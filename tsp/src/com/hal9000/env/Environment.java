package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.FileType;
import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.solver.GreedySolver;
import com.hal9000.solver.HeuristicSolver;
import com.hal9000.solver.Solver;
import com.hal9000.solver.SteepestSolver;
import com.hal9000.time.NTimer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Environment {
    public enum SolverType {GREEDY, STEEPEST, HEURISTIC}

    public List<TSPInstance> instances;


    public Environment(String dict, boolean allowNull, List<FileType> filesDef) {
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
        int it = 0;
        timer.start();
        double dist = 0.0;
        while (true) {
            dist+=Benchmark.optDist(instances.get(instance), createSolver(type, instances.get(instance)).solve());
            it++;
            if (timer.check(N)) break;
        }

        System.out.println("time: " + String.valueOf(timer.result()));
        System.out.println(timer.result() / it);
        System.out.println("opt: ");
        System.out.println(dist / it);

    }

}
