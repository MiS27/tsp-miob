package com.hal9000.data;

import java.util.List;

/**
 * Created by rt on 14.10.15.
 */
public class TSPInstance {
    private String name;
    private String comment;
    private int dim;
    private List<City> vertices;
    public TSPInstance(String name, String comment, int dim){
        this.setName(name);
        this.setComment(comment);
        this.setDim(dim);
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

    public List<City> getVertices() {
        return vertices;
    }

    public void setVertices(List<City> vertices) {
        this.vertices = vertices;
    }
}
