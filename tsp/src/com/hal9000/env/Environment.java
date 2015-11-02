package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.FileType;
import com.hal9000.solver.*;
import com.hal9000.time.NTimer;
import com.hal9000.time.SimpleTimer;
import com.hal9000.time.Timer;

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
    public void run(SolverType type, Timer timer){
        for(int i=0; i< instances.size();i++){
            System.out.println(instances.get(i).getName());
            run(type,timer,i);
        }
    }

    private void run(SolverType type, Timer timer, int instance) {
        Solution solution;
        for(int i=0; i < perInstance;i++) {
            solution = timer.measure(createSolver(type,instances.get(instance)));
            report.addToReport(type.toString(), instances.get(instance), solution);
            System.out.println(solution.getTime());
        }


    }

}
