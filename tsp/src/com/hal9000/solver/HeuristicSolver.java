package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;

import java.util.*;

/** Greedy solver implementation */
public class HeuristicSolver implements Solver {

    private final TSPInstance problem;
    private final Solution solution;

    public HeuristicSolver(TSPInstance problem) {
        this.problem = problem;
        ArrayList<Integer> solution = new ArrayList<>(problem.getDim());

        List<Integer> sequence = new LinkedList<>();
        for (int i = 1; i < problem.getDim(); i++) {
            sequence.add(i);
        }
        solution.add(0);

        int last = 0;
        for (int i = 1; i < problem.getDim(); i++) {
            last = getClosest(last, sequence);
            solution.add(last);
        }
        this.solution = new Solution(solution, problem);
    }

    private int getClosest(int last, List<Integer> sequence) {
        double distance;
        int bestIdx = 0;
        int result ;
        double bestDistance = problem.getDistance(last, sequence.get(0));
        for (int i = 1; i < sequence.size(); i++) {
            distance = problem.getDistance(last, sequence.get(i));
            if (distance < bestDistance) {
                bestDistance = distance;
                bestIdx = i;
            }
        }
        result = sequence.get(bestIdx);
        sequence.remove(sequence.get(bestIdx));
        return result;
    }

    public Solution solve(Arg argument) {
        return solution;
    }

}
