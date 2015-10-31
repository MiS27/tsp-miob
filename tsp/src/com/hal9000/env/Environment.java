package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.FileType;
import com.hal9000.solver.*;
import com.hal9000.time.NTimer;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    public enum SolverType {GREEDY, STEEPEST, HEURISTIC}

    public List<TSPInstance> instances;

    private Report report;

    public Environment(String dict, boolean allowNull, List<FileType> filesDef) {
        report = new Report();
        instances = new ArrayList<>();
        for(FileType ft : filesDef){
            instances.addAll(ft.getInstancesFromDict(dict,allowNull));
        }
    }

    public Solver createSolver(SolverType type, TSPInstance instance) {
        switch (type) {
            case STEEPEST: {
                return new SteepestSolver(instance);
            }
            case GREEDY: {
                return new GreedySolver(instance);
            }
            case HEURISTIC: {
                return new HeuristicSolver(instance);
            }

        }
        return null;
    }
    public void run(SolverType type, int N){
        for(int i=0; i< instances.size();i++){
            System.out.println(instances.get(i).getName());
            run(type,N,i);
        }
    }

    private void run(SolverType type, int N, int instance) {
        NTimer timer = new NTimer();
        //SimpleTimer timer = new SimpleTimer();
        int it = 0;
        timer.start();
        double dist = 0.0;
        while (true) {

            dist+=Benchmark.optDist(instances.get(instance), createSolver(type, instances.get(instance)).solve());
            it++;
            if (timer.check(N)) break;
        }

        //dist+=Benchmark.optDist(instances.get(instance), createSolver(type, instances.get(instance)).solve());
       // timer.stop();



        System.out.println("time: " + String.valueOf(timer.result()));
        System.out.println(timer.result() / it);
        System.out.println("opt: ");
        System.out.println(dist / it);

    }

}
