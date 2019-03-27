package com.bsuir.plugin;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import com.bsuir.lab2.domain.Machine;
import com.bsuir.lab2.plugin.Plugin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlToJsonPlugin implements Plugin {

    @Override
    public void execute(List<Machine> machines) {

        try {
            File file = new File("test.xml");
            List<Machine> machines1 = new XmlMapper().readValue(file, new TypeReference<List<Machine>>() {});
            new ObjectMapper().writeValue(new File("test.json"), machines1);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String getDescription() {
        return "xml to json";
    }
}
