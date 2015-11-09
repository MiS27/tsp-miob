package com.hal9000.time;

import com.hal9000.env.Arg;
import com.hal9000.solver.Solution;
import com.hal9000.solver.Solver;

/**
 * Created by rt on 20.10.15.
 */
public interface Timer {
    void start();
    void stop();
    double result();
    Solution measure(Solver solver, Arg argument);

}
