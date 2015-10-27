package com.hal9000;

import com.hal9000.env.Environment;

import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Map<String, String> input = new TreeMap<>();
        input.put("tsp/test/u574.tsp",null);


        Environment env = new Environment(input);
        env.run(Environment.SolverType.STEEPEST,1);

    }
}



