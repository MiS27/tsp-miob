package com.hal9000;

import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.data.TSPInstance;
import com.hal9000.random.SimpleRandom;
import com.hal9000.time.SimpleTimer;
import com.hal9000.time.Timer;
import com.hal9000.random.*;

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

        Timer timer = new SimpleTimer();
        timer.start();

        SimpleRandom rand = new SimpleRandom(1);
        randomHotBits rr = new randomHotBits();
        for(int i=0; i < 1000; i++) {
            System.out.print(Math.abs(rr.nextInt()%100) + "|");
        }
        timer.stop();
        System.out.println(timer.result());


    }
}
