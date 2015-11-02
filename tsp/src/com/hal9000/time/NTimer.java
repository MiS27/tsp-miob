package com.hal9000.time;

import com.hal9000.solver.Solution;
import com.hal9000.solver.Solver;

/**
 * Created by rt on 20.10.15.
 */
public class NTimer implements Timer{
    private long start,stop;
    private int N;
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
    public Solution measure(Solver solver) {
        Solution solution=null;
        start();
        int it=0;
        while(true){
            solution = solver.solve();
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
