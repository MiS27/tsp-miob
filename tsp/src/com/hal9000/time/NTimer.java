package com.hal9000.time;

/**
 * Created by rt on 20.10.15.
 */
public class NTimer implements Timer{
    private long start,stop;
    public NTimer(){
    }
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
        return Math.floor((stop - start)/1000000000);
    }
    public boolean check(double N){
        stop();
        return result()>=N;
    }
}
