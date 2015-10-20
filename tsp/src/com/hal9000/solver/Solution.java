package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rt on 20.10.15.
 */
public class Solution {

    private ArrayList<Integer> solution;

    private TSPInstance problem;

    //TODO: should accept problem instance
    public Solution(ArrayList<Integer> solution, TSPInstance problem) {
        this.solution = solution;
        this.problem = problem;
    }

    public void move(int a, int b) {
        Collections.swap(solution, a, b);
    }

    public double getMoveDelta(int a, int b) {
        int aPredecessor = getPredecessor(a);
        int aSuccessor = getSuccessor(a);
        int bPredecessor = getPredecessor(b);
        int bSuccessor = getSuccessor(b);
        double positive = problem.getDistance(a, bPredecessor) + problem.getDistance(a, bSuccessor) +
                problem.getDistance(b, aPredecessor) + problem.getDistance(b, aSuccessor);
        double negative = problem.getDistance(a, aPredecessor) + problem.getDistance(a, aSuccessor) +
                problem.getDistance(b, bPredecessor) + problem.getDistance(b, bSuccessor);
        return positive - negative;
    }

    private int getPredecessor(int a) {
        if (a == 0) {
            return solution.size() - 1;
        }
        return a - 1;
    }

    private int getSuccessor(int a) {
        if (a == solution.size() - 1) {
            return 0;
        }
        return a + 1;
    }
}
