package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;

import java.util.*;

/** Greedy solver implementation */
public class HeuristicSolver implements Solver {

    private final TSPInstance problem;
    private final Solution solution;
    private ArrayList<Integer> solutionSequence;
    private double best;

    public HeuristicSolver(TSPInstance problem) {
        this.problem = problem;

        List<Integer> fullSequence = new LinkedList<>();
        for (int i = 0; i < problem.getDim(); i++) {
            fullSequence.add(i);
        }

        List<Integer> sequence = new LinkedList<>();
        for (int i = 0; i < problem.getDim(); i++) {
            sequence.clear();
            sequence.addAll(fullSequence);
            generateSolution(sequence, i);
        }

        this.solution = new Solution(solutionSequence, problem);
    }

    private void generateSolution(List<Integer> sequence, int start) {
        ArrayList<Integer> tmpSolution = new ArrayList<>(problem.getDim());
        double score = 0.0;
        tmpSolution.add(start);
        int last = start;
        for (int i = 1; i < problem.getDim(); i++) {
            double distance;
            int bestIdx = 0;
            double bestDistance = problem.getDistance(last, sequence.get(0));
            for (int j = 1; j < sequence.size(); j++) {
                distance = problem.getDistance(last, sequence.get(j));
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestIdx = j;
                }
            }
            score += bestDistance;
            last = sequence.get(bestIdx);
            tmpSolution.add(last);
            sequence.remove(last);
        }
        score += problem.getDistance(last, start);

        if (solution == null || score < best) {
            solutionSequence = tmpSolution;
            best = score;
        }
    }

    public Solution solve(Arg argument) {
        return solution;
    }

}
