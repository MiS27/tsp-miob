package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rt on 20.10.15.
 */
public class Solution {

    private ArrayList<Integer> solution;
    private double time;
    private int steps;

    private TSPInstance problem;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Solution(ArrayList<Integer> solution, TSPInstance problem) {
        this.solution = solution;
        this.problem = problem;
    }

    public void move(int a, int b) {
        Collections.swap(getSolution(), a, b);
    }

    //TODO: To refactor
    public double getMoveDelta(int a, int b) {
        double positive;
        double negative;
        int aVal = getSolution().get(a);
        int bVal = getSolution().get(b);
        int aPredecessor = getPredecessor(a);
        int aSuccessor = getSuccessor(a);
        int bPredecessor = getPredecessor(b);
        int bSuccessor = getSuccessor(b);
        if (aVal == bPredecessor) {
            positive = problem.getDistance(aVal, bSuccessor) + problem.getDistance(bVal, aPredecessor);
            negative = problem.getDistance(aVal, aPredecessor) + problem.getDistance(bVal, bSuccessor);
        } else if (aVal == bSuccessor) {
            positive = problem.getDistance(aVal, bPredecessor) + problem.getDistance(bVal, aSuccessor);
            negative = problem.getDistance(aVal, aSuccessor) + problem.getDistance(bVal, bPredecessor);
        } else {
            positive = problem.getDistance(aVal, bPredecessor) + problem.getDistance(aVal, bSuccessor) +
                    problem.getDistance(bVal, aPredecessor) + problem.getDistance(bVal, aSuccessor);
            negative = problem.getDistance(aVal, aPredecessor) + problem.getDistance(aVal, aSuccessor) +
                    problem.getDistance(bVal, bPredecessor) + problem.getDistance(bVal, bSuccessor);
        }
        return positive - negative;
    }

    private int getPredecessor(int a) {
        if (a == 0) {
            return getSolution().get(getSolution().size() - 1);
        }
        return getSolution().get(a - 1);
    }

    private int getSuccessor(int a) {
        if (a == getSolution().size() - 1) {
            return getSolution().get(0);
        }
        return getSolution().get(a + 1);
    }

    public ArrayList<Integer> getSolution() {
        return solution;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
