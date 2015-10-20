package com.hal9000.search;

import com.hal9000.data.TSPInstance;

/**
 * Created by rt on 19.10.15.
 */
public interface LocalSearch {
    void perform(TSPInstance instance);
}
