package com.hal9000.data;

import java.util.List;

/**
 * Created by rt on 14.10.15.
 */
public class TSPInstance {
    private String name;
    private String comment;
    private int dim;
    private List<City> cities;
    private double[][] distMatrix;
    public TSPInstance(String name, String comment, int dim, List<City> cities){
        this.setName(name);
        this.setComment(comment);
        this.setDim(dim);
        this.cities = cities;
        distMatrix = new double[cities.size()][cities.size()];
        recalculate();
    }

    public void recalculate(){
        for(int i = 0 ; i < cities.size(); i++){
            for(int j = 0 ; j < cities.size(); j++){
                distMatrix[i][j] = Math.sqrt(
                                    Math.pow(cities.get(i).getX()-cities.get(j).getX(),2.0)+
                                    Math.pow(cities.get(i).getY()-cities.get(j).getY(),2.0));
            }
        }
    }

    public double getDistance(int i, int j){
        return distMatrix[i][j];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
