package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

/**
 * Created by rt on 19.10.15.
 */
public class SteepestSolver extends LocalSearchSolver {

    public SteepestSolver(TSPInstance problem) {
        super(problem);
    }

    @Override
    protected boolean step() {
        double delta = 0;
        double tmpDelta;
        int bestA = 0;
        int bestB = 0;

        boolean improved = false;
        for (int i = 0; i < problem.getDim(); i++) {
            for (int j = i + 1; j < problem.getDim(); j++) {
                tmpDelta = solution.getMoveDelta(i, j);
                if (tmpDelta < delta) {
                    delta = tmpDelta;
                    bestA = i;
                    bestB = j;
                }
            }
        }

        if (delta < 0) {
            solution.move(bestA, bestB);
            improved = true;
        }

        return improved;
    }

}
