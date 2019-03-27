package com.bsuir.lab6;


import java.nio.file.Paths;
import java.util.List;

import com.bsuir.lab2.domain.Machine;
import com.bsuir.lab2.plugin.Plugin;
import task6.MachinesJSONReader;

/**
 * Adapter pattern for plugin from other developer
 */
public class JSONReaderPlugin implements Plugin {

    private final MachinesJSONReader writer = new MachinesJSONReader();


    @Override
    public void execute(List<Machine> machines) {
        writer.read(Paths.get("machines.json"));
    }

    @Override
    public String getDescription() {
        return "JSON Reader";
    }
}
