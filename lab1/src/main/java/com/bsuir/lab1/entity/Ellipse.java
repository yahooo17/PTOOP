package com.bsuir.lab1.entity;

public class Ellipse extends Shape {

    private Point center;
    private double radiusX;
    private double radiusY;

    public Ellipse(Point center, double radiusX, double radiusY) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    private static Class[] getConstructParams() {
        return new Class[]{Point.class, Double.TYPE, Double.TYPE};
    }

}
