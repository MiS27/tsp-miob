package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

import java.util.ArrayList;

/** Solution representation */
public class Solution {

    private ArrayList<Integer> solution;
    private double time;
    private int steps;
    private int checked;
    private double startCost;

    private TSPInstance problem;

    public double getQuality(){

        double a = (problem.getCost(getSolution())/problem.getOptimalValue())-1;
        double b = problem.getDim()/time;
        return ((problem.getCost(getSolution())/problem.getOptimalValue())-1);
    }

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

    public ArrayList<Integer> getSolution() {
        return solution;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public TSPInstance getProblem() {
        return problem;
    }

    public double getStartCost() {
        return startCost;
    }

    public void setStartCost(double startCost) {
        this.startCost = startCost;
    }
}
