package com.bsuir.lab1.entity;


public class ShapeList {

    private Shape[] shapeList;
    private int count;
    private int initialSize = 10;

    public ShapeList() {
        this.shapeList = new Shape[initialSize];
        this.count = 0;
    }

    public void add(Shape shape) {
        this.ensureCapacity();
        this.shapeList[count++] = shape;
    }

    public Shape get(int index) {
        return this.shapeList[index];
    }

    public int size() {
        return this.shapeList.length;
    }

    private void ensureCapacity(){
        if (count == initialSize) {
            initialSize = ((this.shapeList.length * 3)/2) + 1;
            Shape[] newShapeList = new Shape[initialSize];
            for (int i = 0; i < this.shapeList.length; i++) {
                newShapeList[i] = this.shapeList[i];
            }
            this.shapeList = newShapeList;
        }
    }

}
