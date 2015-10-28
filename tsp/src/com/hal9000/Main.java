package com.hal9000;

import com.hal9000.env.Environment;
import com.hal9000.parsers.FileType;
import com.hal9000.parsers.SimpleParser;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<FileType> filesDefs = new ArrayList<>();
        filesDefs.add(new FileType(".tsp",".opt.tour",new SimpleParser()));

        Environment env = new Environment("tsp/test",false, filesDefs);
        env.run(Environment.SolverType.STEEPEST,1);

    }
}



