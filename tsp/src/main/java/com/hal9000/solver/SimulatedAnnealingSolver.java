package com.hal9000.solver;

import com.google.common.math.DoubleMath;
import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;
import com.hal9000.solver.move.Opt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealingSolver extends RandomSolver {

    private final Random random;

    protected  TSPInstance problem;

    private int lk;

    private double alpha;

    private int c;

    private int stop;

    private ArrayList<Integer> bestSequence;

    private double bestCost;

    private double currentCost;

    public SimulatedAnnealingSolver(TSPInstance problem, int lk, double alpha, int stop) {
        super(problem);
        this.problem = problem;
        this.lk = lk;
        this.alpha = alpha;
        this.stop = stop;
        random = new Random();
    }

    public Solution solve(Arg argument) {
        int counter = 0;
        int steps = 0;
        c = generateTemperature((Opt) argument);
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
        solution.setSolution(bestSequence);
        return solution;
    }

    private boolean step(Arg argument) {
        boolean improved = false;
        for (int l = 0; l < lk; l++) {
            if (tryMove((Opt) argument)) {
                improved = true;
            }
            solution.setChecked(solution.getChecked()+1);
        }

        return improved;
    }

    private boolean tryMove(Opt argument) {
        boolean improved = false;
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

        double delta = argument.getMoveDelta(i, j, solution);

        if (delta  < -Environment.eps) {
            argument.move(i, j, solution);
            currentCost += delta;
            if (currentCost < bestCost) {
                bestCost = currentCost;
                bestSequence = new ArrayList<>(solution.getSolution());
            }
            improved = true;
        } else if (Math.exp(-delta/c) > Math.random()) {
            argument.move(i, j, solution);
        }
        return improved;
    }

    private int generateTemperature(Opt argument) {
        int result = 0;
        int solutions = 1000;
        int moves = 100;
        List<Double> deltas = new ArrayList<>(solutions*moves);
        int deltaZero = 0;
        for (int k = 0; k < solutions; k++) {
            Collections.shuffle(solution.getSolution());
            for (int l = 0; l < moves; l++) {
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

                double delta = Math.abs(argument.getMoveDelta(i, j, solution));
                if (delta < Environment.eps) {
                    deltaZero++;
                } else {
                    deltas.add(delta);
                }
            }
        }
        double mean = DoubleMath.mean(deltas);
        if (deltaZero < solutions * moves) {
            result = (int) Math.ceil(-mean/Math.log(1 - 0.1/(1-deltaZero/(solutions*moves))));
        }
        return result;
    }

}
