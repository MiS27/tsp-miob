package com.hal9000.random;

public class SimpleRandom implements Random{
    private long last=1;
    private long seed;
    private long b = 12344132;
    public SimpleRandom(int seed){
        this.seed =seed;
    }

    @Override
    public int nextInt(int n, boolean unsigned) {
        last = (System.nanoTime() * last + System.currentTimeMillis());
        last = unsigned ? Math.abs(last) : last;
        return (int)(last%n);
    }
    @Override
    public int nextInt(boolean unsigned) {
        last = (System.nanoTime() * last + System.currentTimeMillis());
        last = unsigned ? Math.abs(last) : last;
        return (int)(last%Integer.MAX_VALUE);
    }
    @Override
    public double nextDouble() {
        return 0;
    }
}
