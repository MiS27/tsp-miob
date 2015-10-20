package com.hal9000.random;

import java.security.SecureRandom;

/**
 * Created by rt on 20.10.15.
 */
public class URandom implements Random{
    private SecureRandom random;
    public URandom(){
        random = new SecureRandom();
    }

    @Override
    public int nextInt(boolean unsinged) {
        int rand = random.nextInt();
        rand = unsinged ? Math.abs(rand) : rand;
        return rand;
    }

    @Override
    public int nextInt(int n, boolean unsigned) {
        int rand = random.nextInt(n);
        rand = unsigned ? Math.abs(rand) : rand;
        return rand;
    }

    @Override
    public double nextDouble() {
        return 0;
    }
}
