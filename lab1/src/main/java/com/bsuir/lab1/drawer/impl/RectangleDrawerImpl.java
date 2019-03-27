package com.bsuir.lab1.drawer.impl;

import com.bsuir.lab1.drawer.Drawer;
import com.bsuir.lab1.entity.Rectangle;


public class RectangleDrawerImpl implements Drawer<Rectangle> {

    private static final RectangleDrawerImpl instance = new RectangleDrawerImpl();

    private RectangleDrawerImpl() {

    }

    public static RectangleDrawerImpl getInstance() {
        return instance;
    }

    @Override
    public javafx.scene.shape.Shape draw(Rectangle shape) {
        javafx.scene.shape.Rectangle fxRectangle = new javafx.scene.shape.Rectangle(shape.getPointA().getX(),
                shape.getPointA().getY(), shape.getWidth(), shape.getHeight());
        return fxRectangle;
    }
}
