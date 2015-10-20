package com.hal9000;

import com.hal9000.parsers.Parser;
import com.hal9000.parsers.SimpleParser;
import com.hal9000.data.TSPInstance;
import com.hal9000.random.SimpleRandom;
import com.hal9000.time.SimpleTimer;
import com.hal9000.time.Timer;

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
        for(int i=0; i < 100000; i++) {
            System.out.println(rand.nextInt(100, true));
            System.out.println(rand.nextInt(100, true));
            System.out.println(rand.nextInt(100, true));
            System.out.println(rand.nextInt(100, true));
            System.out.println(rand.nextInt(100, true));
            System.out.println(rand.nextInt(100, true));
            System.out.println(rand.nextInt(100, true));
        }
        timer.stop();
        System.out.println(timer.result());


    }
}
