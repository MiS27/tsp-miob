package com.hal9000.solver;

import java.util.Collections;

public class City2Opt implements Opt{
    @Override
    public void move(int i, int j, Solution solution) {
        Collections.swap(solution.getSolution(), i, j);
    }

    @Override
    public double getMoveDelta(int i, int j, Solution solution) {
        double positive;
        double negative;
        int aVal = solution.getSolution().get(i);
        int bVal = solution.getSolution().get(j);
        int aPredecessor = getPredecessor(i, solution);
        int aSuccessor = getSuccessor(i,solution);
        int bPredecessor = getPredecessor(j,solution);
        int bSuccessor = getSuccessor(j,solution);
        if (aVal == bPredecessor) {
            positive = solution.getProblem().getDistance(aVal, bSuccessor) + solution.getProblem().getDistance(bVal, aPredecessor);
            negative = solution.getProblem().getDistance(aVal, aPredecessor) + solution.getProblem().getDistance(bVal, bSuccessor);
        } else if (aVal == bSuccessor) {
            positive = solution.getProblem().getDistance(aVal, bPredecessor) + solution.getProblem().getDistance(bVal, aSuccessor);
            negative = solution.getProblem().getDistance(aVal, aSuccessor) + solution.getProblem().getDistance(bVal, bPredecessor);
        } else {
            positive = solution.getProblem().getDistance(aVal, bPredecessor) + solution.getProblem().getDistance(aVal, bSuccessor) +
                    solution.getProblem().getDistance(bVal, aPredecessor) + solution.getProblem().getDistance(bVal, aSuccessor);
            negative = solution.getProblem().getDistance(aVal, aPredecessor) + solution.getProblem().getDistance(aVal, aSuccessor) +
                    solution.getProblem().getDistance(bVal, bPredecessor) + solution.getProblem().getDistance(bVal, bSuccessor);
        }
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
