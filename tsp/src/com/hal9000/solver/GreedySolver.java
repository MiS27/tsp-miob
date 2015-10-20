package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

/**
 * Created by rt on 19.10.15.
 */
public class GreedySolver extends LocalSearchSolver {

    public GreedySolver(TSPInstance problem) {
        super(problem);
    }

    @Override
    protected boolean step() {
        boolean improved = false;
        for (int i = 0; !improved && i < problem.getDim(); i++) {
            for (int j = i + 1; !improved && j < problem.getDim(); j++) {
                if (solution.getMoveDelta(i, j) < 0) {
                    solution.move(bestA, bestB);
                    improved = true;
                }
            }
        }
        return improved;
    }

}
