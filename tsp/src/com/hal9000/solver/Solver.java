package com.hal9000.solver;

import com.hal9000.data.TSPInstance;

/**
 * Created by rt on 19.10.15.
 */
public interface Solver {
    void perform(TSPInstance instance);
}
