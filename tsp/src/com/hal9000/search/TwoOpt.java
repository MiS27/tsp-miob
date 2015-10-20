package com.hal9000.search;

import com.hal9000.data.City;

import java.util.List;

/**
 * Created by rt on 20.10.15.
 */
public class TwoOpt {
    public static void perform(List<City> cities, int x, int y){
        City tmp = cities.get(x);
        cities.set(x,cities.get(y));
        cities.set(y,tmp);
    }
}
