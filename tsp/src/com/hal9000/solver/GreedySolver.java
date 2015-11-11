package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;

/** Greedy solver implementation */
public class GreedySolver extends LocalSearchSolver {

    public GreedySolver(TSPInstance problem) {
        super(problem);
    }

    @Override
    protected boolean step(Arg argument) {
        boolean improved = false;
        for (int i = 0; !improved && i < problem.getDim(); i++) {
            for (int j = i + 1; !improved && j < problem.getDim(); j++) {
                /*if (solution.getMoveDelta(i, j) < 0) {
                    solution.move(i, j);
                    improved = true;
                }*/
                if (((Opt)argument).getMoveDelta(i, j, solution) < 0) {
                    ((Opt)argument).move(i, j,solution);
                    improved = true;
                }
                solution.setChecked(solution.getChecked()+1);
            }
        }
        return improved;
    }

}
