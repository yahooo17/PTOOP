package com.bsuir.lab1.entity;


public class Rectangle extends Shape {

    private Point pointA;
    private Double width;
    private Double height;

    public Rectangle(Point a, double width, double height) {
        this.pointA = a;
        this.width = width;
        this.height = height;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private static Class[] getConstructParams() {
        return new Class[]{Point.class, Double.TYPE, Double.TYPE};
    }
}
