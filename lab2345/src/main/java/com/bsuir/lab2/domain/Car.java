package com.bsuir.lab2.domain;

public class Car extends Machine {

    private static final long serialVersionUID = 8004949830156881967L;

    private String fuelType;

    public Car() {
        super("car");
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public void performWork() {
        System.out.println("Drive");
    }

    @Override
    public String toString() {
        return "Car{" + "fuelType='" + fuelType + '\'' + "} " + super.toString();
    }
}
