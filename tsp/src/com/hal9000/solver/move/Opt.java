package com.hal9000.solver.move;

import com.hal9000.env.Arg;
import com.hal9000.solver.Solution;

/** Common interface for opt operators
 * @see Arg
 */
public interface Opt extends Arg {
    void move(int i, int j, Solution solution);
    double getMoveDelta(int i, int j, Solution solution);
}
