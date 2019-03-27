package com.bsuir.lab1.drawer.impl;

import com.bsuir.lab1.drawer.Drawer;
import com.bsuir.lab1.entity.Ellipse;
import javafx.scene.shape.Shape;


public class EllipseDrawerImpl implements Drawer<Ellipse> {

    private static final EllipseDrawerImpl instance = new EllipseDrawerImpl();

    private EllipseDrawerImpl() {

    }

    public static EllipseDrawerImpl getInstance() {
        return instance;
    }

    @Override
    public Shape draw(Ellipse shape) {
        javafx.scene.shape.Ellipse fxEllipse = new javafx.scene.shape.Ellipse(shape.getCenter().getX(), shape.getCenter().getY(),
                shape.getRadiusX(), shape.getRadiusY());
        return fxEllipse;
    }

}
