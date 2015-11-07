package com.hal9000.env;

import com.hal9000.data.TSPInstance;
import com.hal9000.solver.Solution;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Report{
    public Map<String, Map<TSPInstance, List<Solution>>> solutions;

    public Report(){
        solutions = new TreeMap<>();
    }

    public void addToReport(String key, TSPInstance instance, Solution solution){
        if(!solutions.containsKey(key)) solutions.put(key, new TreeMap<TSPInstance, List<Solution>>());
        if(!solutions.get(key).containsKey(instance))  solutions.get(key).put(instance,new ArrayList<Solution>());

        solutions.get(key).get(instance).add(solution);

    }

    public void dump(String file){


        try{
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));

            for(String s : solutions.keySet()){

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

