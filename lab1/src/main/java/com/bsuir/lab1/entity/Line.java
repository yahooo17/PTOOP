package com.bsuir.lab1.entity;


public class Line extends Shape {

    private Point pointA;
    private Point pointB;

    public Line(Point a, Point b) {
        this.pointA = a;
        this.pointB = b;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    private static Class[] getConstructParams() {
        return new Class[]{Point.class, Point.class};
    }
}
