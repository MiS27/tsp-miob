package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.solver.GreedySolver;
import com.hal9000.solver.HeuristicSolver;
import com.hal9000.solver.Solver;
import com.hal9000.solver.SteepestSolver;
import com.hal9000.time.NTimer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Environment {
    public enum SolverType {GREEDY, STEEPEST, HEURISTIC}

    public List<TSPInstance> instances;

    public Environment(Map<String, String> input) {
        getInstances(input);
    }

    private void getInstances(Map<String, String> input) {
        Parser parser = new SimpleParser();
        instances = new ArrayList<>();
        try {
            for (String in : input.keySet()) {
                if (null == input.get(in)) {
                    instances.add(parser.parse(new FileInputStream(in)));
                } else {
                    instances.add(parser.parse(new FileInputStream(in), new FileInputStream(input.get(in))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Environment(String dict, boolean allowNull) {
        File dir = new File(dict);
        File[] files = dir.listFiles();
        Map<String, String> input = new TreeMap<>();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith("tsp")) {
                input.put(dict+"/"+f.getName(), null);
                continue;
            }
            if (f.isFile() && f.getName().endsWith("tour")) {
                input.put(dict+"/"+f.getName().split("\\.")[0] + ".tsp", dict+"/"+f.getName());
            }

        }

        if (!allowNull) {

            Map<String, String> tmp = new TreeMap<>();

            for (String k : input.keySet()) {
                if (null != input.get(k)) {
                    tmp.put(k, input.get(k));
                }
            }
            getInstances(tmp);
            return;
        }

        getInstances(input);

    }

    public Solver createSolver(SolverType type, TSPInstance instance) {
        switch (type) {
            case STEEPEST: {
                return new SteepestSolver(instance);
            }
            case GREEDY: {
                return new GreedySolver(instance);
            }
            case HEURISTIC: {
                return new HeuristicSolver(instance);
            }

        }
        return null;
    }
    public void run(SolverType type, int N){
        for(int i=0; i< instances.size();i++){
            System.out.println(instances.get(i).getName());
            run(type,N,i);
        }
    }

    private void run(SolverType type, int N, int instance) {
        NTimer timer = new NTimer();
        int it = 0;
        timer.start();
        while (true) {
            createSolver(type,instances.get(instance)).solve();
            it++;
            if (timer.check(N)) break;
        }

        System.out.println("time: " + String.valueOf(timer.result()));
        System.out.println(timer.result() / it);

    }

}
