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

        while (perform) {
            perform = step();
        }

        return solution;
    }

    abstract protected boolean step();

}
