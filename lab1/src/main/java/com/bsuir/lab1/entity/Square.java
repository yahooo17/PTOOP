package com.bsuir.lab1.entity;


public class Square extends Shape {

    private Point pointA;
    private double width;

    public Square(Point a, double width) {
        this.pointA = a;
        this.width =width;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    private static Class[] getConstructParams() {
        return new Class[]{Point.class, Double.TYPE};
    }
}
