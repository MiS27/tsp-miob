package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rt on 19.10.15.
 */
public class RandomSolver implements Solver {

    protected Solution solution;

    public RandomSolver(TSPInstance problem) {
        ArrayList<Integer> sequence = new ArrayList<>(problem.getDim());
        for (int i = 0; i < problem.getDim(); i++) {
            sequence.add(i);
        }
        Collections.shuffle(sequence);
        solution = new Solution(sequence, problem);
    }

    public Solution solve() {
        return solution;
    }

}
