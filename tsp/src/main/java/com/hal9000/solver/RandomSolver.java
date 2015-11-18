package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.LongArg;
import com.hal9000.env.TimeArg;

import java.util.ArrayList;
import java.util.Collections;

/** Random solver implementation*/
public class RandomSolver implements Solver {

    protected Solution solution;
    private TSPInstance problem;


    public RandomSolver(TSPInstance problem) {
        ArrayList<Integer> sequence = new ArrayList<>(problem.getDim());
        for (int i = 0; i < problem.getDim(); i++) {
            sequence.add(i);
        }
        Collections.shuffle(sequence);
        solution = new Solution(sequence, problem);
        solution.setStartCost(problem.getCost(sequence));
        solution.setSteps(1);
        this.problem = problem;
    }

    public Solution solve(Arg argument) {
        ArrayList<Integer> sequence = new ArrayList<>(problem.getDim());
        for (int i = 0; i < problem.getDim(); i++) {
            sequence.add(i);
        }
        long start=System.nanoTime();
        long time = ((LongArg)argument).arg;

        while(System.nanoTime()-start < time) {
            Collections.shuffle(sequence);
            if(problem.getCost(sequence)< problem.getCost(solution.getSolution())){
                solution.setSolution(new ArrayList<Integer>(sequence));
            }
            solution.setSteps(solution.getSteps() + 1);
        }
        return solution;
    }

}
