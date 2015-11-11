package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;

/** Local search solver
 *  @see GreedySolver
 *  @see SteepestSolver
 */
public abstract class LocalSearchSolver extends RandomSolver {

    protected  TSPInstance problem;

    public LocalSearchSolver(TSPInstance problem) {
        super(problem);
        this.problem = problem;
    }

    public Solution solve(Arg argument) {
        boolean perform = true;
        int steps = 0;
        while (perform) {
            perform = step(argument);
            steps++;
        }

        solution.setSteps(steps);
        return solution;
    }

    abstract protected boolean step(Arg argument);

}
