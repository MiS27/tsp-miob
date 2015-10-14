package com.hal9000.parsers;

import java.util.Map;

/**
 * Created by rt on 14.10.15.
 */
public class TSPInstance<T> {
    private String name;
    private String comment;
    private int dim;
    private Float[][] connections;
    public TSPInstance(String name, String comment, int dim){
        this.name = name;
        this.comment = comment;
        this.dim = dim;
    }
}
