package com.bsuir.lab1.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<Class> getClasses(String packageName) throws RuntimeException {
        try {

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                return null;
            }

            URL resource = classLoader.getResource(packageName.replace('.', '/'));
            if (resource == null) {
                return null;
            }

            List<File> files =
                    Files.walk(new File(resource.toURI().toString().replace("file:", "")).toPath())
                            .map(path -> new File(path.toUri()))
                            .collect(Collectors.toList());

            List<Class> classes = new ArrayList<>();
            for (File file : files) {
                if (file.getName().endsWith(".class")) {
                    String filePath =
                            Paths.get(resource.toURI()).relativize(file.toPath()).toString();
                    // remove .class and add base package name
                    String className =
                            packageName
                                    + '.'
                                    + filePath.substring(0, filePath.length() - 6)
                                            .replace('\\', '.')
                                            .replace('/', '.');
                    classes.add(Class.forName(className));
                }
            }
            return classes;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
