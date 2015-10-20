package com.hal9000.time;

import com.hal9000.data.TSPInstance;
import com.hal9000.search.LocalSearch;

/**
 * Created by rt on 20.10.15.
 */
public class EstTime {
    public static double measure(LocalSearch search, TSPInstance instance, int n, Timer timer){
        timer.start();
        for(int i=0; i < n ;i++){
            search.perform(instance);
        }
        timer.stop();
        return timer.result()/(double)n;
    }
}
