package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;
import com.hal9000.solver.move.Opt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class TabuSearchSolver extends LocalSearchSolver {
    private int[][] mat;
    private LinkedList<Move> tabuList;
    private List<Move> candidates;
    private Move worstCandidate;
    private int tabuSize, cSize;
    private Move[][] mSpace;

    private double tLen, cLen;
    private int stopLimit;
    private int sols=0;

    private Solution currentSolution;


    public TabuSearchSolver(TSPInstance problem, double tLen, double cLen, int stopLimit) {
        super(problem);
        this.tLen = tLen;
        this.cLen = cLen;
        mat = new int[problem.getDim()][problem.getDim()];
        candidates = new ArrayList<>();

        tabuSize = (int)(problem.getDim()*tLen) +1;
        tabuList = new LinkedList<Move>();

        cSize = (int)(problem.getDim()*cLen) +1;
        this.stopLimit = Math.max(stopLimit,(int)(0.5*problem.getDim()));
        mSpace = new Move[problem.getDim()][problem.getDim()];

        for (int i = 0; i < problem.getDim(); i++) {
            for (int j = i + 1; j < problem.getDim(); j++) {
                mSpace[i][j] = new Move(i,j,0.0,false);
            }
        }
        currentSolution = new Solution(new ArrayList<Integer>(solution.getSolution()),problem);

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
        solution.setChecked(sols);
        System.out.println(sols);
        return solution;
    }

    private void getCandidates(Arg argument){
        candidates.clear();
        List<Move> tmp = new ArrayList<>();
        for (int i = 0; i < problem.getDim(); i++) {
            for (int j = i + 1; j < problem.getDim(); j++) {
                sols++;
                double tmpDelta = ((Opt)argument).getMoveDelta(i,j,currentSolution);//+mat[i][j];
                mSpace[i][j].setDelta(tmpDelta);
                tmp.add(mSpace[i][j]);
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
        double bestdelta = 10000000.0;
        boolean perform = false;
        Move bestCanditate = null;

        for(Move m : candidates){
            sols++;
            double delta = ((Opt)argument).getMoveDelta(m.getX(),m.getY(),currentSolution);
            if(m.isTabu()){
                delta = ((Opt)argument).getMoveDelta(m.getX(),m.getY(),solution);
            }
            if(bestdelta > delta){

                bestdelta = delta;
                bestCanditate = m;
            }
        }

        ((Opt)argument).move(bestCanditate.getX(),bestCanditate.getY(),currentSolution);
        tabuList.add(bestCanditate);
        mat[bestCanditate.getX()][bestCanditate.getY()]++;
        if(tabuList.size() >= tabuSize){
            Move m = tabuList.poll();
            mat[m.getX()][m.getY()]--;
            //tabuList.removeIf(m::same);

        }


        if(problem.getCost(currentSolution.getSolution()) < problem.getCost(solution.getSolution())){
            solution.setSolution(new ArrayList<>(currentSolution.getSolution()));
            perform = true;
        }


        if(((Opt)argument).getMoveDelta(worstCandidate.getX(),worstCandidate.getY(),currentSolution)
                < ((Opt)argument).getMoveDelta(bestCanditate.getX(),bestCanditate.getY(),currentSolution)){
            getCandidates(argument);
        }


        return perform;

    }

    class Move implements Comparable<Move> {
        int x, y;
        Double delta;
        boolean tabu;

        public void setTabu(boolean tabu) {
            this.tabu = tabu;
        }

        public boolean isTabu() {
            return tabu;
        }

        public Move(int x, int y, double delta, boolean t) {
            setX(x);
            setY(y);
            setDelta(delta);
            setTabu(t);
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

        public boolean same(Move move){
            return move.getX() == getX() && move.getY() ==getY();
        }
        @Override
        public int compareTo(Move move) {
            return getDelta().compareTo(move.getDelta());
        }
    }
}
