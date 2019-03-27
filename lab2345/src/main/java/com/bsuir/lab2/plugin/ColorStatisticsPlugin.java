package com.bsuir.lab2.plugin;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bsuir.lab2.domain.Machine;

/**
 * Plugin which collects statistics about colors of machines
 */
public class ColorStatisticsPlugin implements Plugin {

    @Override
    public void execute(List<Machine> machines) {
        if (machines == null || machines.isEmpty()) {
            System.out.println("No statistic available. Please add machines");
            return;
        }

        Map<String, Long> collect =
                machines.stream()
                        .filter(
                                machine ->
                                        machine.getColor() != null && !machine.getColor().isEmpty())
                        .collect(Collectors.groupingBy(Machine::getColor, Collectors.counting()));

        if (collect.keySet().isEmpty()) {
            System.out.println("No statistic available. Please add machines");
            return;
        }

        System.out.println("\nColor statistic:");
        collect.forEach(
                (key, value) -> System.out.println(value + " machines have " + key + " color"));
    }

    @Override
    public String getDescription() {
        return "View color statistic";
    }
}
