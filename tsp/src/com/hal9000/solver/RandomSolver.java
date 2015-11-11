package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;

import java.util.ArrayList;
import java.util.Collections;

/** Random solver implementation*/
public class RandomSolver implements Solver {

    protected Solution solution;

    public RandomSolver(TSPInstance problem) {
        ArrayList<Integer> sequence = new ArrayList<>(problem.getDim());
        for (int i = 0; i < problem.getDim(); i++) {
            sequence.add(i);
        }
        Collections.shuffle(sequence);
        solution = new Solution(sequence, problem);
        solution.setSteps(1);
    }

    public Solution solve(Arg argument) {
        return solution;
    }

}
