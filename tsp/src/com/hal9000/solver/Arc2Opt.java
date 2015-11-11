package com.hal9000.solver;

import java.util.Collections;

/** 2-opt implementation (arcs exchange) */
public class Arc2Opt implements Opt{
    @Override
    public void move(int i, int j, Solution solution) {
        for (int k = 0; k < (j - i + 1) / 2; k++) {
            Collections.swap(solution.getSolution(), i + k, j - k);
        }

    }

    @Override
    public double getMoveDelta(int i, int j, Solution solution) {
        double positive;
        double negative;
        int aVal = solution.getSolution().get(i);
        int bVal = solution.getSolution().get(j);
        int aPredecessor = getPredecessor(i, solution);
        int bSuccessor = getSuccessor(j, solution);
        positive = solution.getProblem().getDistance(aVal, bSuccessor) + solution.getProblem().getDistance(bVal, aPredecessor);
        negative = solution.getProblem().getDistance(aVal, aPredecessor) + solution.getProblem().getDistance(bVal, bSuccessor);
        return positive - negative;
    }

    private int getPredecessor(int a, Solution solution) {
        if (a == 0) {
            return solution.getSolution().get(solution.getSolution().size() - 1);
        }
        return solution.getSolution().get(a - 1);
    }

    private int getSuccessor(int a, Solution solution) {
        if (a == solution.getSolution().size() - 1) {
            return solution.getSolution().get(0);
        }
        return solution.getSolution().get(a + 1);
    }
}
