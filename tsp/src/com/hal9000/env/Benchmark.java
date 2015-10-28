package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.solver.Solution;

public class Benchmark {

    public static double optDist(TSPInstance instance, Solution solution){
        return instance.getCost(solution.getSolution()) - instance.getOptimalValue();
    }
}
