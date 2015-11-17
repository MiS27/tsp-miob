package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;
import com.hal9000.solver.move.Opt;

/** Steepest solver implementation */
public class SteepestSolver extends LocalSearchSolver {

    public SteepestSolver(TSPInstance problem) {
        super(problem);
    }

    @Override
    protected boolean step(Arg argument) {
        double delta = 0;
        double tmpDelta;
        int bestA = 0;
        int bestB = 0;

        boolean improved = false;
        for (int i = 0; i < problem.getDim(); i++) {
            for (int j = i + 1; j < problem.getDim(); j++) {
                tmpDelta = ((Opt)argument).getMoveDelta(i,j,solution);

                solution.setChecked(solution.getChecked()+1);
                if (tmpDelta < delta) {
                    delta = tmpDelta;
                    bestA = i;
                    bestB = j;
                }
            }
        }


        if (delta < -Environment.eps) {
            ((Opt)argument).move(bestA,bestB,solution);
            improved = true;
        }

        return improved;
    }

}
