package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.random.Random;
import com.hal9000.solver.Solver;
import com.hal9000.time.NTimer;
import com.hal9000.time.Timer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rt on 21.10.15.
 */
public class Environment {

    public List<TSPInstance> instances;

    public Environment(String[] inputs){
        Parser parser = new SimpleParser();
        instances = new ArrayList<>();
        try {
            for (String input : inputs) {
                instances.add(parser.parse(new FileInputStream(input)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(Solver solver, int N){
        NTimer timer = new NTimer();
        int it=0;
        timer.start();
        while(true){
            solver.solve();
            it++;
            if(timer.check(N)) break;
        }

        System.out.println("time: " + String.valueOf(timer.result()));
        System.out.println(timer.result()/it);

    }

}
