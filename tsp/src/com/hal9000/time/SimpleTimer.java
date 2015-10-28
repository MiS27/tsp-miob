package com.hal9000.time;

import java.util.Date;

/**
 * Created by rt on 20.10.15.
 */
public class SimpleTimer implements Timer{

    private long start,stop;
    @Override
    public void start() {
        start = 0;
        stop = 0;
        start = System.nanoTime();
    }

    @Override
    public void stop() {
        stop = System.nanoTime();
    }

    @Override
    public double result() {
        return stop - start;
    }
}
