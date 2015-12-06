package com.hal9000.solver;

import com.hal9000.data.TSPInstance;
import com.hal9000.env.Arg;
import com.hal9000.env.Environment;

import java.util.ArrayList;
import java.util.List;

public class TabuSearchSolver extends LocalSearchSolver{
    private int[][] mat;
    private List<Move> tabuList;
    private List<Move> candidates;

    private final int TABU_LEN_FAC = 4;
    private final int CAND_FAC = 10;
    private final int STOP_LIM = 5;
    private List<Move> getCandiates(){
        return null;
    }

    public TabuSearchSolver(TSPInstance problem) {
        super(problem);
        mat = new int[problem.getDim()][problem.getDim()];
        tabuList = new ArrayList<>(problem.getDim()/TABU_LEN_FAC);
        int candLen = 0;
        if(problem.getDim()/CAND_FAC  == 0 ) candLen =1;
        candidates = new ArrayList<>(candLen);
    }

    @Override
    protected boolean step(Arg argument) {
        int stop = 0;
        double best = 0.0;
        double lastBest = 0.0;
        while(stop<STOP_LIM){


            if(Math.abs(lastBest - best) < Environment.eps){
                stop++;
            }else
            {
                stop = 0;
            }
        }
        return false;
    }

    class Move implements Comparable<Move>{
        int x,y;
        public Move(int x, int y){
            setX(x);
            setY(y);
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


        @Override
        public int compareTo(Move move) {
            if(move.getX() == getX() && move.getY() == getY()) return  0;
            return 1;
        }
    }
}
