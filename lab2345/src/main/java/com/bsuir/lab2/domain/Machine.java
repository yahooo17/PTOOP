package com.bsuir.lab2.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base parent class for machine classes hierarchy
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "machineType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GrassCutter.class, name = "grassCutter"),
        @JsonSubTypes.Type(value = WashingMachine.class, name = "washingMachine"),
        @JsonSubTypes.Type(value = CopyMachine.class, name = "copyMachine"),
        @JsonSubTypes.Type(value = Computer.class, name = "computer"),
        @JsonSubTypes.Type(value = Car.class, name = "car"),
        @JsonSubTypes.Type(value = CoffeeMachine.class, name = "coffeeMachine")

})
public abstract class Machine implements Serializable {

    private static final long serialVersionUID = -1872641596064542849L;

    private String serialNumber;
    private String color;
    private String machineType;

    protected Machine(String machineType) {
        this.machineType = machineType;
    }

    public abstract void performWork();

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    @Override
    public String toString() {
        return "Machine{"
                + "serialNumber='"
                + serialNumber
                + '\''
                + ", color='"
                + color
                + '\''
                + '}';
    }
}
