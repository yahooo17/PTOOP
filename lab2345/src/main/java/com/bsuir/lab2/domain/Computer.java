package com.bsuir.lab2.domain;

public class Computer extends Machine {

    private static final long serialVersionUID = 5229516636386502604L;

    private String processor;

    public Computer() {
        super("computer");
    }

    public Computer(String processor) {
        super("computer");
        this.processor = processor;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public void performWork() {
        System.out.println("Compute smth");
    }

    @Override
    public String toString() {
        return "Computer{" + "processor='" + processor + '\'' + "} " + super.toString();
    }
}
