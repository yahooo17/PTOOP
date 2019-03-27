package com.bsuir.lab1.drawer.impl;

import com.bsuir.lab1.drawer.Drawer;
import com.bsuir.lab1.entity.Circle;
import javafx.scene.shape.Shape;


public class CircleDrawerImpl implements Drawer<Circle> {

    private static final CircleDrawerImpl instance = new CircleDrawerImpl();

    private CircleDrawerImpl() {

    }

    public static CircleDrawerImpl getInstance() {
        return instance;
    }

    @Override
    public Shape draw(Circle shape) {
        javafx.scene.shape.Circle fxCircle = new javafx.scene.shape.Circle(shape.getCenter().getX(),
                shape.getCenter().getY(), shape.getRadius());
        return fxCircle;
    }
}
