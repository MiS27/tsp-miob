package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;
import com.hal9000.solver.move.Opt;

import java.util.Random;

public class SimulatedAnnealingSolver extends RandomSolver {

    private final Random random;

    protected  TSPInstance problem;

    private int lk;

    private double alpha;

    private int c;

    private int stop;

    public SimulatedAnnealingSolver(TSPInstance problem, int lk, double alpha, int c, int stop) {
        super(problem);
        this.problem = problem;
        this.lk = lk;
        this.alpha = alpha;
        this.c = c;
        this.stop = stop;
        random = new Random();
    }

    public Solution solve(Arg argument) {
        int counter = 0;
        int steps = 0;
        while (counter < stop) {
            if (!step(argument)) {
                counter++;
            } else {
                counter = 0;
            }
            steps++;
            c = (int)(alpha * c);
        }

        solution.setSteps(steps);
        return solution;
    }

    private boolean step(Arg argument) {
        boolean improved = false;
        for (int l = 0; l < lk; l++) {
            int i = random.nextInt(problem.getDim());
            // -1 because i != j
            int j = random.nextInt(problem.getDim() - 1);

            if (j < i) {
                int tmp = j;
                j = i;
                i = tmp;
            } else {
                // +1 because i != j
                j++;
            }

            double delta = ((Opt) argument).getMoveDelta(i, j, solution);

            if (delta  < -Environment.eps) {
                ((Opt) argument).move(i, j, solution);
                improved = true;
            } else if (Math.exp(-delta/c) > Math.random()) {
                ((Opt) argument).move(i, j, solution);
            }
            solution.setChecked(solution.getChecked()+1);
        }

        return improved;
    }

}
