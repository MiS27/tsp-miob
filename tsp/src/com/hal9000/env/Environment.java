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
    private int perInstance;
    private Report report;

    public Environment(String dict, boolean allowNull, List<FileType> filesDef, int perInstance) {
        this.perInstance = perInstance;
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
        Solution solution;
        for(int i=0; i < perInstance;i++) {
            int it = 0;
            timer.start();
            while (true) {
                solution = createSolver(type, instances.get(instance)).solve();
                it++;
                if (timer.check(N)) break;
            }

            solution.setTime(timer.result() / it);
            report.addToReport(type.toString(), instances.get(instance), solution);

        }


    }

}
