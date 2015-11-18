package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.FileType;
import com.hal9000.solver.*;
import com.hal9000.time.Timer;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class
 */
public class Environment {
    /**
     * List of available solvers
     */
    public enum SolverType {
        GREEDY, STEEPEST, HEURISTIC, RANDOM
    }

    public final static double eps = 0.00000000001;
    public List<TSPInstance> instances;
    private int perInstance;
    private Report report;
    public static ArrayList<Long> time;
    public static long div;

    /**
     * @param dict        path to directory with instances
     * @param allowNull   allow to parse instances without optimal solution
     * @param filesDef    list of files definitions
     * @param perInstance number of tests for one instance
     */
    public Environment(String dict, boolean allowNull, List<FileType> filesDef, int perInstance) {
        this.perInstance = perInstance;
        report = new FullReport();
        instances = new ArrayList<>();
        for (FileType ft : filesDef) {
            instances.addAll(ft.getInstancesFromDict(dict, allowNull));
        }
        time = new ArrayList<>();
        for (int i = 0; i < instances.size(); i++) {
            time.add(0l);
        }
    }

    public Report getReport() {
        return report;
    }

    /**
     * Creates solver
     *
     * @param type     solver type
     * @param instance instance of problem
     * @return solver created for specific instance
     * @see SteepestSolver
     * @see GreedySolver
     * @see RandomSolver
     * @see HeuristicSolver
     */
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
            case RANDOM: {
                return new RandomSolver(instance);
            }

        }
        return null;
    }

    /**
     * Runs tests
     *
     * @param type     solver type
     * @param timer    timer for measurments
     * @param argument arguments for solver
     */
    public void run(SolverType type, String name, Timer timer, Arg argument) {
        if (type == SolverType.RANDOM && argument!=null) {
                for (int i = 0; i < instances.size(); i++) {
                System.out.println(instances.get(i).getName());
                run(type, name, timer, i, new LongArg(((TimeArg)argument).time.get(i)/Environment.div));
            }
        } else {
            for (int i = 0; i < instances.size(); i++) {
                System.out.println(instances.get(i).getName());
                run(type, name, timer, i, argument);
            }
        }

    }

    private void run(SolverType type, String name, Timer timer, int instance, Arg argument) {
        Solution solution = null;
        for (int i = 0; i < perInstance; i++) {
            solution = timer.measure(createSolver(type, instances.get(instance)), argument);
            if(type != SolverType.RANDOM && type != SolverType.HEURISTIC) time.set(instance,time.get(instance)+Math.round(solution.getTime()*1000000000));
            report.addToReport(name, instances.get(instance), solution);
        }
    }

}
