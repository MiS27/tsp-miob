package com.hal9000.solver;

import com.hal9000.env.Arg;
import com.hal9000.solver.Solution;

public interface Opt extends Arg {
    void move(int i, int j, Solution solution);
    double getMoveDelta(int i, int j, Solution solution);
}
