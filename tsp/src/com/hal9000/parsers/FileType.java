package com.hal9000.parsers;

import com.hal9000.data.TSPInstance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileType {
    private String mainExt;
    private String resExt;
    private Parser parser;

    public FileType(String mainExtension, String resultExtension, Parser parser){
        this.mainExt = mainExtension;
        this.resExt = resultExtension;
        this.parser = parser;
    }

    private List<TSPInstance> getInstances(Map<String, String> input){

        Parser parser = new SimpleParser();
        List<TSPInstance> instances = new ArrayList<>();
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
        return instances;
    }

    public List<TSPInstance> getInstancesFromDict(String dict, boolean allowNull) {
        File dir = new File(dict);
        File[] files = dir.listFiles();
        Map<String, String> input = new TreeMap<>();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(mainExt)) {
                input.put(dict+"/"+f.getName(), dict+"/"+f.getName().split("\\.")[0]+resExt);
                //continue;
            }
/*
            System.out.println(input.get(f.getName()));
            if (f.isFile() && f.getName().endsWith(resExt)) {
                input.put(dict+"/"+f.getName().split("\\.")[0] + mainExt, dict+"/"+f.getName());
            }
*/
        }

        if (!allowNull) {

            Map<String, String> tmp = new TreeMap<>();
            for (String k : input.keySet()) {
                if (null != input.get(k)) {
                    tmp.put(k, input.get(k));
                }
            }
            return getInstances(tmp);
        }

        return getInstances(input);

    }


}