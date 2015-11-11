package com.hal9000.data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TSPInstance implements Comparable<TSPInstance>{
    private Integer id = ++ID;
    private static Integer ID=0;
    private String name;
    private String comment;
    private int dim;
    //private List<City> cities;
    private Map<Integer, City> cities;
    private double[][] distMatrix;

    private List<Integer> optimal=null;
    private double optimalValue=-1.0;


    public TSPInstance(String name, String comment, int dim, Map<Integer,City> cities){
        this.setName(name);
        this.setComment(comment);
        this.setDim(dim);
        this.cities = cities;
        distMatrix = new double[dim+1][dim+1];
        recalculate();
    }


    public void recalculate(){
        for(int i = 1 ; i < dim+1; i++){
            for(int j = 1 ; j < dim+1; j++){
                distMatrix[i][j] = Math.sqrt(
                                    Math.pow(cities.get(i).getX()-cities.get(j).getX(),2.0)+
                                    Math.pow(cities.get(i).getY()-cities.get(j).getY(),2.0));
            }
        }
    }


    public double getCost(List<Integer> src){
        double opt = 0.0;
        for(int i =0; i<src.size()-1;i++){
            //TODO
            opt += getDistance(src.get(i), src.get(i + 1));
        }

        opt += getDistance(src.get(0), src.get(src.size()-1));
        return opt;
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

    public Map<Integer, City> getCities() {
        return cities;
    }

    public void setCities(Map<Integer,City> cities) {
        this.cities = cities;
    }

    public List<Integer> getOptimal() {
        return optimal;
    }

    public void setOptimal(List<Integer> optimal) {
        this.optimal = optimal;
        this.optimalValue = getCost(optimal);
    }

    public double getOptimalValue() {
        return optimalValue;
    }

    public void setOptimalValue(double optimalValue) {
        this.optimalValue = optimalValue;
    }

    @Override
    public int compareTo(TSPInstance instance) {
        return id.compareTo(instance.id);
    }
}
