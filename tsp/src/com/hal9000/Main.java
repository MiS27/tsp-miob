package com.hal9000;

import com.hal9000.env.Environment;
import com.hal9000.solver.SteepestSolver;

public class Main {

    public static void main(String[] args) {
        Environment env = new Environment(new String[]{"tsp/test/u574.tsp"});
        env.run(new SteepestSolver(env.instances.get(0)),1);

    }
}
