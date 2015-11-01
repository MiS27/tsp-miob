package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

/**
 * Created by rt on 19.10.15.
 */
public abstract class LocalSearchSolver extends RandomSolver {

    protected  TSPInstance problem;

    public LocalSearchSolver(TSPInstance problem) {
        super(problem);
        this.problem = problem;
    }

    public Solution solve() {
        boolean perform = true;
        int steps = 0;
        while (perform) {
            perform = step();
            steps++;
        }

        solution.setSteps(steps);
        return solution;
    }

    abstract protected boolean step();

}
