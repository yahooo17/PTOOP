package com.bsuir.lab2.plugin;

import java.util.List;

import com.bsuir.lab2.domain.Machine;

/**
 * Common interface for plugins
 */
public interface Plugin {

    void execute(List<Machine> machines);

    String getDescription();
}
