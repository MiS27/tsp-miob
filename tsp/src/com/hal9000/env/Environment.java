package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.FileType;
import com.hal9000.solver.*;
import com.hal9000.time.NTimer;
import com.hal9000.time.SimpleTimer;
import com.hal9000.time.Timer;

import java.util.ArrayList;
import java.util.List;

/** Main class*/
public class Environment {
    /** List of available solvers */
    public enum SolverType {GREEDY, STEEPEST, HEURISTIC, RANDOM}

    public List<TSPInstance> instances;
    private int perInstance;
    private Report report;

    /**
     *
     * @param dict path to directory with instances
     * @param allowNull allow to parse instances without optimal solution
     * @param filesDef list of files definitions
     * @param perInstance number of tests for one instance
     */
    public Environment(String dict, boolean allowNull, List<FileType> filesDef, int perInstance) {
        this.perInstance = perInstance;
        report = new FullReport();
        instances = new ArrayList<>();
        for(FileType ft : filesDef){
            instances.addAll(ft.getInstancesFromDict(dict,allowNull));
        }
    }

    public Report getReport() {
        return report;
    }

    /** Creates solver
     *  @see SteepestSolver
     *  @see GreedySolver
     *  @see RandomSolver
     *  @see HeuristicSolver
     * @param type solver type
     * @param instance instance of problem
     * @return solver created for specific instance
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
            case RANDOM:{
                return new RandomSolver(instance);
            }

        }
        return null;
    }

    /** Runs tests
     *
     * @param type solver type
     * @param timer timer for measurments
     * @param argument arguments for solver
     */
    public void run(SolverType type, Timer timer, Arg argument){
        for(int i=0; i< instances.size();i++){
            System.out.println(instances.get(i).getName());
            run(type,timer,i, argument);
        }
    }

    private void run(SolverType type, Timer timer, int instance, Arg argument) {
        Solution solution;
        for(int i=0; i < perInstance;i++) {
            System.out.println(i);
            solution = timer.measure(createSolver(type,instances.get(instance)),argument);
            report.addToReport(type.toString(), instances.get(instance), solution);
        }


    }

}
