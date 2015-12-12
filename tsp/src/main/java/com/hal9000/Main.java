package com.hal9000;

import com.hal9000.env.Environment;
import com.hal9000.env.TimeArg;
import com.hal9000.parsers.FileType;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.solver.move.Arc2Opt;
import com.hal9000.solver.move.City2Opt;
import com.hal9000.time.SimpleTimer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<FileType> filesDefs = new ArrayList<>();
        filesDefs.add(new FileType(".tsp", ".opt.tour", new SimpleParser()));


        Environment.div= 40000000000000000l;
        //warmup
       /* Environment env = new Environment("tsp/testfiles",false, filesDefs,30);
        env.run(Environment.SolverType.STEEPEST,"steepest-arc-dummy", new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.STEEPEST,"steepest-city-dummy", new SimpleTimer(), new City2Opt());
        env.run(Environment.SolverType.GREEDY,"greedy-arc-dummy",new SimpleTimer(), new Arc2Opt());
        env.run(Environment.SolverType.GREEDY,"greedy-city-dummy",new SimpleTimer(), new City2Opt());
        //env.run(Environment.SolverType.HEURISTIC,"heuristic-dummy", new SimpleTimer(), null);
        //env.run(Environment.SolverType.RANDOM,"random-dummy",new SimpleTimer(),new TimeArg(Environment.time));
*/

        Environment.div= 40l;
        Environment env = new Environment("tsp/testfiles",false, filesDefs,10);
        env.run(Environment.SolverType.TABU,"tabu", new SimpleTimer(), new Arc2Opt());
        env.getReport().dump("full-report-TS.csv", "|", false);

        /*System.out.println("Steepest-arc");
        env.run(Environment.SolverType.STEEPEST,"steepest-arc", new SimpleTimer(), new Arc2Opt());
        System.out.println("Steepest-city");
        env.run(Environment.SolverType.STEEPEST,"steepest-city", new SimpleTimer(), new City2Opt());
        System.out.println("Greedy-arc");
        env.run(Environment.SolverType.GREEDY, "greedy-arc", new SimpleTimer(), new Arc2Opt());
        System.out.println("Greedy-city");
        env.run(Environment.SolverType.GREEDY,"greedy-city",new SimpleTimer(), new City2Opt());
        //env.run(Environment.SolverType.HEURISTIC,"heuristic", new SimpleTimer(), null);
        //env.run(Environment.SolverType.RANDOM,"random",new SimpleTimer(),new TimeArg(Environment.time));
        //env.getReport().dump("full-report-sc-s.csv", "|", false);

  /*Environment      env = new Environment("tsp/test2",false, filesDefs,2000);
        env.run(Environment.SolverType.GREEDY,"greedy-city",new SimpleTimer(), new City2Opt());
        env.getReport().dump("full-report-gs.csv", "|", false);
*/
    }
}



