package com.hal9000.time;

import com.hal9000.env.Arg;
import com.hal9000.solver.Solution;
import com.hal9000.solver.Solver;

/** Timer interface */
public interface Timer {
    void start();
    void stop();
    double result();
    Solution measure(Solver solver, Arg argument);

}
