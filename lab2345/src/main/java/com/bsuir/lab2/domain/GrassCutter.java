package com.bsuir.lab2.domain;

public class GrassCutter extends Machine {

    private static final long serialVersionUID = 2105577925406875932L;

    private String type;

    public GrassCutter() {
        super("grassCutter");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void performWork() {
        System.out.println("Cut grass");
    }

    @Override
    public String toString() {
        return "GrassCutter{" + "type='" + type + '\'' + "} " + super.toString();
    }
}
