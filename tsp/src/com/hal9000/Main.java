package com.hal9000;

import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.parsers.TSPInstance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        TSPInstance instance = null;
        try {
            Parser p = new SimpleParser();
            instance = p.parse(new FileInputStream("u574.tsp"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(instance.getName());
    }
}
