package com.hal9000;

import com.hal9000.env.Environment;
import com.hal9000.parsers.FileType;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.solver.Arc2Opt;
import com.hal9000.solver.City2Opt;
import com.hal9000.time.SimpleTimer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<FileType> filesDefs = new ArrayList<>();
        filesDefs.add(new FileType(".tsp", ".opt.tour", new SimpleParser()));

        Environment env = new Environment("tsp/test",false, filesDefs,10);

        env.run(Environment.SolverType.RANDOM,new SimpleTimer(),null);
        //env.run(Environment.SolverType.STEEPEST, new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.GREEDY,new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.HEURISTIC, new SimpleTimer(), null);
/*
        System.out.println("1");
        env.getReport().dump("full-report.csv", "|", false);

        /*env = new Environment("tsp/test",false, filesDefs,10);

        env.run(Environment.SolverType.STEEPEST, new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.GREEDY, new SimpleTimer(), new Arc2Opt());

        env.getReport().dump("full-report-arc.csv", "|", false);

*/
   /*     System.out.println("2");

        env = new Environment("tsp/test",false, filesDefs,10);

        env.run(Environment.SolverType.GREEDY, new SimpleTimer(), new City2Opt());

        env.getReport().dump("full-report-greedy-city.csv", "|", false);


        System.out.println("3");

/*
        env = new Environment("tsp/test",false, filesDefs,10);

        env.run(Environment.SolverType.GREEDY, new SimpleTimer(), new Arc2Opt());

        env.getReport().dump("full-report-greedy-arc.csv", "|", false);
*/
 /*       System.out.println("4");

         env = new Environment("tsp/test",false, filesDefs,300);

        env.run(Environment.SolverType.GREEDY, new SimpleTimer(), new City2Opt());

        env.getReport().dump("full-report-greedy-city-end-start.csv", "|", false);

        System.out.println("5");
/*
         env = new Environment("tsp/test",false, filesDefs,300);

        env.run(Environment.SolverType.GREEDY,new SimpleTimer(), new Arc2Opt());

        env.getReport().dump("full-report-greedy-arc-end-start.csv", "|", false);

*/






    }
}



