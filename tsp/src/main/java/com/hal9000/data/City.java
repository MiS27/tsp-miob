package com.hal9000.data;


/** City representation*/
public class City {
    private double x;
    private double y;
    private int id;

    /**
     *
     * @param id unique city id
     * @param x x coord
     * @param y y coord
     */
    public City(int id, double x, double y){
        this.setId(id);
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
