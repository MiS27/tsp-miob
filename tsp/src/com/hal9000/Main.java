package com.hal9000;

import com.hal9000.env.Environment;

public class Main {

    public static void main(String[] args) {
        Environment env = new Environment("tsp/test",false);
        env.run(Environment.SolverType.STEEPEST,1);

    }
}



