package com.hal9000.time;

import com.hal9000.env.Arg;
import com.hal9000.solver.Solution;
import com.hal9000.solver.Solver;

import java.util.Date;


/** Simple timer implementation using System.nanoTime() */
public class SimpleTimer implements Timer{

    private long start,stop;
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
        return stop - start;
    }

    @Override
    public Solution measure(Solver solver, Arg argument) {
        start();
        Solution solution = solver.solve(argument);
        stop();
        solution.setTime(result()/1000000000);
        return  solution;
    }
}
