package com.hal9000.parsers;

import java.io.*;

/**
 * Created by rt on 14.10.15.
 *
 * a280.tsp
 *
 *
 */
public class SimpleParser implements Parser{
    @Override
    public TSPInstance parse(InputStream input) throws IOException {
        String name="";
        String dim=""
        String comment="", line;
        String[] pair;
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(reader);


        while((line = br.readLine()) != null){
            pair = line.split(":");
            name = pair[0].trim().equals("NAME") ? name = pair[1].trim() : name;
            dim = pair[0].trim().equals("DIMENSION") ? dim = pair[1].trim() : dim;
            comment = pair[0].trim().equals("COMMENT") ? comment = pair[1].trim() : comment;



        }

        TSPInstance out = new TSPInstance<>(name,comment,Integer.parseInt(dim));

        return out;
    }
}
