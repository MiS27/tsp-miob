package com.hal9000.parsers;

import com.hal9000.data.TSPInstance;
import com.hal9000.data.City;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Parser for files like a280 */
public class SimpleParser implements Parser{

    @Override
    public TSPInstance parse(InputStream input) throws IOException {
        String name="";
        String dim="";
        String comment="", line;
        String[] pair, coord;
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(reader);
        Map<Integer, City> v = new TreeMap<>();
        boolean flag=true;

        while((line = br.readLine()) != null){
            coord = line.trim().split(" +");
            if(coord[0].trim().equals("NODE_COORD_SECTION")) {flag = false; continue;}
            if(flag) {
                pair = line.split(":");
                name = pair[0].trim().equals("NAME") ? name = pair[1].trim() : name;
                dim = pair[0].trim().equals("DIMENSION") ? dim = pair[1].trim() : dim;
                comment = pair[0].trim().equals("COMMENT") ? comment = pair[1].trim() : comment;
            }else {
                if(coord[0].trim().equals("EOF")) break;
                v.put(Integer.parseInt(coord[0])-1,
                        new City(Integer.parseInt(coord[0])-1,Double.parseDouble(coord[1]), Double.parseDouble(coord[2])));
            }

        }

        TSPInstance out = new TSPInstance(name,comment,Integer.parseInt(dim),v);
        return out;
    }

    @Override
    public TSPInstance parse(InputStream input, InputStream test) throws IOException {
        TSPInstance out = parse(input);
        InputStreamReader reader = new InputStreamReader(test);
        BufferedReader br = new BufferedReader(reader);
        List<Integer> tour = new ArrayList<Integer>();
        boolean flag = true;
        String line;
        while((line = br.readLine()) != null){
            if(line.trim().equals("TOUR_SECTION")) {flag = false; continue;}
            if(!flag) {
                Integer i = Integer.parseInt(line);
                if (i < 0) break;
                tour.add(i-1);
            }


        }
        out.setOptimal(tour);

        return out;
    }
}
