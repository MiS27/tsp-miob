package com.hal9000.time;

import com.hal9000.env.Arg;
import com.hal9000.solver.Solution;
import com.hal9000.solver.Solver;


/** Timer implementation for low-accuracy time measure(1s) */
public class NTimer implements Timer{
    private long start,stop;
    private int N;

    /**
     *
     * @param N  width of time window
     */
    public NTimer(int N){
        this.N = N;
    }
    @Override
    public void start() {
        start = 0;
        stop = 0;
        start = System.nanoTime();
    }

    @Override
    public void stop() {
        stop = System.nanoTime();
    }

    @Override
    public double result() {
        return Math.floor((stop - start)/1000000000);
    }

    @Override
    public Solution measure(Solver solver, Arg argument) {
        Solution solution=null;
        start();
        int it=0;
        while(true){
            solution = solver.solve(argument);
            it++;
            if(check(N)) break;
        }
        stop();
        solution.setTime(result()/it);
        return solution;

    }

    public boolean check(double N){
        stop();
        return result()>=N;
    }
}
