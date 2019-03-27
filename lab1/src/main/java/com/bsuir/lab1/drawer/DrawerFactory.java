package com.bsuir.lab1.drawer;

import java.util.HashMap;
import java.util.Map;

import com.bsuir.lab1.drawer.impl.CircleDrawerImpl;
import com.bsuir.lab1.drawer.impl.EllipseDrawerImpl;
import com.bsuir.lab1.drawer.impl.LineDrawerImpl;
import com.bsuir.lab1.drawer.impl.RectangleDrawerImpl;
import com.bsuir.lab1.drawer.impl.SquareDrawerImpl;
import com.bsuir.lab1.drawer.impl.TriangleDrawerImpl;
import com.bsuir.lab1.entity.Circle;
import com.bsuir.lab1.entity.Ellipse;
import com.bsuir.lab1.entity.Line;
import com.bsuir.lab1.entity.Rectangle;
import com.bsuir.lab1.entity.Shape;
import com.bsuir.lab1.entity.Square;
import com.bsuir.lab1.entity.Triangle;

public class DrawerFactory {

    private static final Map<Class<? extends Shape>, Drawer<? extends Shape>> drawers = new HashMap<>();

    static {
        drawers.put(Rectangle.class, RectangleDrawerImpl.getInstance());
        drawers.put(Triangle.class, TriangleDrawerImpl.getInstance());
        drawers.put(Circle.class, CircleDrawerImpl.getInstance());
        drawers.put(Square.class, SquareDrawerImpl.getInstance());
        drawers.put(Line.class, LineDrawerImpl.getInstance());
        drawers.put(Ellipse.class, EllipseDrawerImpl.getInstance());
    }

    public static <T extends Shape> Drawer<T> get(Class<T> clz) {
        return (Drawer<T>) drawers.get(clz);
    }

}
