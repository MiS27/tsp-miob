package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;
import com.hal9000.solver.move.Opt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabuSearchSolver extends LocalSearchSolver {
    private int[][] mat;
    private List<Move> tabuList;
    private List<Move> candidates;
    private Move worstCandidate;
    private int tabuSize, cSize;

    private double tLen, cLen;
    private int stopLimit;


    public TabuSearchSolver(TSPInstance problem, double tLen, double cLen, int stopLimit) {
        super(problem);
        mat = new int[problem.getDim()][problem.getDim()];
        candidates = new ArrayList<>();

        tabuSize = (int)(problem.getDim()*tLen) +1;
        tabuList = new ArrayList<Move>(tabuSize);

        this.tLen = tLen;
        this.cLen = cLen;
        cSize = (int)(problem.getDim()*cLen) +1;
        this.stopLimit = stopLimit;
    }

    @Override
    public Solution solve(Arg argument) {
        boolean perform = true;
        int steps = 0;
        int stop = 0;
        getCandidates(argument);
        while (perform) {
            perform = step(argument);

            if (!perform) {
                stop++;
            } else {
                stop = 0;
            }
            if (stop < stopLimit) perform = true;
            steps++;
        }

        solution.setSteps(steps);
        return solution;
    }

    private void getCandidates(Arg argument){
        candidates.clear();
        List<Move> tmp = new ArrayList<>();
        for (int i = 0; i < problem.getDim(); i++) {
            for (int j = i + 1; j < problem.getDim(); j++) {
                if(mat[i][j] > 0) continue;
                double tmpDelta = ((Opt)argument).getMoveDelta(i,j,solution);
                tmp.add(new Move(i,j,tmpDelta));
            }
        }
        Collections.sort(tmp);
        for (int i = 0; i < cSize; i++) {
           candidates.add(tmp.get(i));
        }
        worstCandidate = candidates.get(candidates.size()-1);

    }

    @Override
    protected boolean step(Arg argument) {
        double bestdelta = 0.0;
        boolean perform = false;
        Move bestCanditate = null;


        bestdelta = ((Opt)argument).getMoveDelta(candidates.get(0).getX(), candidates.get(0).getY(), this.solution);
        bestCanditate = candidates.get(0);
        for(int i=1; i<candidates.size();i++){
            double delta = ((Opt)argument).getMoveDelta(candidates.get(i).getX(), candidates.get(i).getY(), this.solution);
            if(delta - bestdelta < Environment.eps) {
                bestdelta = candidates.get(i).getDelta();
                bestCanditate = candidates.get(i);
            }
        }
        for(Move m : tabuList){
            double delta = ((Opt)argument).getMoveDelta(m.getX(), m.getY(), this.solution);
            if(delta <= -Environment.eps && delta-bestdelta < Environment.eps ){
                bestdelta = delta;
                bestCanditate = m;
            }
        }


        if(bestdelta < -Environment.eps) {
            perform = true;
            ((Opt) argument).move(bestCanditate.getX(), bestCanditate.getY(), this.solution);
        }
        if(candidates.contains(bestCanditate)){
            candidates.remove(bestCanditate);
        }
        if(candidates.size() == 0 || bestdelta > ((Opt)argument).getMoveDelta(worstCandidate.getX(),worstCandidate.getY(),this.solution)){
            getCandidates(argument);
        }
        tabuList.add(bestCanditate);

        mat[bestCanditate.getX()][bestCanditate.getY()]++;
        if(tabuList.size() == tabuSize){
            mat[tabuList.get(0).getX()][tabuList.get(0).getY()]--;
            tabuList.remove(0);
        }



        return perform;

    }

    class Move implements Comparable<Move> {
        int x, y;
        Double delta;

        public Move(int x, int y, double delta) {
            setX(x);
            setY(y);
            setDelta(delta);
        }

        public void setDelta(double delta) {
            this.delta = delta;
        }

        public Double getDelta() {
            return delta;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean same(int i, int j){
            return i == getX() && j ==getY();
        }
        @Override
        public int compareTo(Move move) {
            return getDelta().compareTo(move.getDelta());
        }
    }
}
