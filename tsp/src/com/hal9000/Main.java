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
/*
        Environment env = new Environment("tsp/test",false, filesDefs,10);
        env.run(Environment.SolverType.RANDOM,"random",new SimpleTimer(),null);
        env.run(Environment.SolverType.STEEPEST,"steepest-arc", new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.STEEPEST,"steepest-city", new SimpleTimer(), new City2Opt());
        env.run(Environment.SolverType.GREEDY,"greedy-arc",new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.GREEDY,"greedy-city",new SimpleTimer(), new City2Opt());
        env.run(Environment.SolverType.HEURISTIC,"heuristic", new SimpleTimer(), null);
        env.getReport().dump("full-report.csv", "|", false);
*/
  Environment      env = new Environment("tsp/test2",false, filesDefs,2000);
        env.run(Environment.SolverType.GREEDY,"greedy-city",new SimpleTimer(), new City2Opt());
        env.getReport().dump("full-report-gs.csv", "|", false);

    }
}



