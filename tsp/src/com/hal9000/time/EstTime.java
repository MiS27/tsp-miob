package com.hal9000.time;

import com.hal9000.data.TSPInstance;
import com.hal9000.solver.Solver;

/**
 * Created by rt on 20.10.15.
 */
public class EstTime {
    public static double measure(Solver search, TSPInstance instance, int n, Timer timer){
        timer.start();
        for(int i=0; i < n ;i++){
        }
        timer.stop();
        return timer.result()/(double)n;
    }
}
