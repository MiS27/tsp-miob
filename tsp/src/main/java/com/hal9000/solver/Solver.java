package com.hal9000.solver;


import com.hal9000.env.Arg;

/** Common solver interface
 * @see Arg
 */
public interface Solver {
    Solution solve(Arg argument);
}
