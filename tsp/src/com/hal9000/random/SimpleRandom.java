package com.hal9000.random;

import java.util.Date;
import java.util.Timer;

/**
 * Created by rt on 20.10.15.
 */
public class SimpleRandom implements Random{
    private long last=1;
    private long seed;
    private long b = 12344132;
    public SimpleRandom(int seed){
        this.seed =seed;
    }

    @Override
    public int nextInt() {
        last = (System.nanoTime() * last + System.currentTimeMillis());
        return (int)(last%Integer.MAX_VALUE);
    }

    @Override
    public double nextDouble() {
        return 0;
    }
}
