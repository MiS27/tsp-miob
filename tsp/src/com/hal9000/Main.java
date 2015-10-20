package com.hal9000;

import com.hal9000.env.Environment;
import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.data.TSPInstance;
import com.hal9000.random.SimpleRandom;
import com.hal9000.random.URandom;
import com.hal9000.solver.GreedySolver;
import com.hal9000.solver.SteepestSolver;
import com.hal9000.thirdParty.RandomHotBits;
import com.hal9000.time.SimpleTimer;
import com.hal9000.time.Timer;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Environment env = new Environment(new String[]{"u574.tsp"});
        env.run(new SteepestSolver(env.instances.get(0)),1);

    }
}
