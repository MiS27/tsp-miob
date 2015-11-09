package com.hal9000;

import com.hal9000.env.Environment;
import com.hal9000.parsers.FileType;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.solver.City2Opt;
import com.hal9000.time.SimpleTimer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<FileType> filesDefs = new ArrayList<>();
        filesDefs.add(new FileType(".tsp", ".opt.tour", new SimpleParser()));

        Environment env = new Environment("tsp/test",false, filesDefs,10);
        //env.run(Environment.SolverType.RANDOM,new SimpleTimer());
        env.run(Environment.SolverType.STEEPEST,new SimpleTimer(), new City2Opt());
        //env.run(Environment.SolverType.GREEDY,new SimpleTimer());
        //env.run(Environment.SolverType.HEURISTIC,new SimpleTimer());

        env.getReport().dump("full-report.csv", "|", true);

    }
}



