package com.bsuir.lab2.domain;

public class CopyMachine extends Machine {

    private static final long serialVersionUID = -2271623348130438177L;

    private String inkType;

    public CopyMachine() {
        super("copyMachine");
    }

    public String getInkType() {
        return inkType;
    }

    public void setInkType(String inkType) {
        this.inkType = inkType;
    }

    @Override
    public void performWork() {
        System.out.println("Copy");
    }

    @Override
    public String toString() {
        return "CopyMachine{" + "inkType='" + inkType + '\'' + "} " + super.toString();
    }
}
