package com.bsuir.lab2.plugin;


import java.util.List;

import com.bsuir.lab2.domain.Machine;

/**
 * Plugin executes perform work of every machine
 */
public class MachineRunnerPlugin implements Plugin {

    @Override
    public void execute(List<Machine> machines) {
        if (machines == null || machines.isEmpty()) {
            System.out.println("Can perform work. Please add machines");
            return;
        }

        machines.forEach(Machine::performWork);
    }

    @Override
    public String getDescription() {
        return "Perform work";
    }
}
