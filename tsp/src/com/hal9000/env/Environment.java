package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.random.Random;
import com.hal9000.solver.GreedySolver;
import com.hal9000.solver.HeuristicSolver;
import com.hal9000.solver.Solver;
import com.hal9000.solver.SteepestSolver;
import com.hal9000.time.NTimer;
import com.hal9000.time.Timer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Environment {
    public enum SolverType {GREEDY, STEEPEST, HEURISTIC}
    public List<TSPInstance> instances;

    public Environment(Map<String, String> input){
        Parser parser = new SimpleParser();
        instances = new ArrayList<>();
        try {
            for (String in : input.keySet()) {
                if(null == input.get(in)){
                    instances.add(parser.parse(new FileInputStream(in)));
                }else{
                    instances.add(parser.parse(new FileInputStream(in), new FileInputStream(input.get(in))));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Solver createSolver(SolverType type){
        switch (type){
            case STEEPEST:{
                return new SteepestSolver(instances.get(0));
            }
            case GREEDY:{
                return new GreedySolver(instances.get(0));
            }
            case HEURISTIC:{
                return new HeuristicSolver(instances.get(0));
            }

        }
        return null;
    }
    public void run(SolverType type, int N){
        NTimer timer = new NTimer();
        int it=0;
        timer.start();
        while(true){
            createSolver(type).solve();
            it++;
            if(timer.check(N)) break;
        }

        System.out.println("time: " + String.valueOf(timer.result()));
        System.out.println(timer.result()/it);

    }

}
