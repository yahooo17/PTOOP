package com.bsuir.lab2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.bsuir.lab1.util.Utils;
import com.bsuir.lab2.domain.Machine;
import com.bsuir.lab2.plugin.Plugin;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.reflect.FieldUtils;

public class Runner {

    private static List<Class> machineClasses;
    //machines list
    private static List<Machine> machines = new ArrayList<>();

    private static Map<Integer, String> actions = new HashMap<>();
    private static Map<Integer, Plugin> plugins = new HashMap<>();


    private static Scanner scanner = new Scanner(System.in);

    static {
        Class<Machine> parentClz = Machine.class;

        //getting classes from hierarchy
        machineClasses =
                Utils.getClasses("com.bsuir.lab2.domain")
                        .stream()
                        .filter(clz -> clz.getSuperclass().equals(parentClz))
                        .filter(clz -> !Modifier.isAbstract(clz.getModifiers()))
                        .filter(clz -> !clz.isInterface())
                        .collect(Collectors.toList());

        actions.put(1, "Add machine");
        actions.put(2, "Modify machine");
        actions.put(3, "Remove machine");
        actions.put(4, "View machines");
        actions.put(5, "Serialize machines");
        actions.put(6, "Deserialize machines");
        actions.put(7, "Exit");

        //Loading all classes from classpath that implements Plugin interface
        for (Plugin plugin : ServiceLoader.load(Plugin.class)) {
            plugins.put(actions.size() + plugins.size() + 1, plugin);
        }
    }

    public static void main(String[] args) throws Exception {

        // user interface
        while (true) {
            System.out.println("///////Menu///////");
            System.out.println("\nAvailable actions:");
            actions.forEach((key, value) -> System.out.println(key + ". " + value));

            System.out.println("\nAvailable plugins:");
            plugins.forEach(
                    (key, value) -> System.out.println(key + ". " + value.getDescription()));

            System.out.println("\nPlease choose one:");
            System.out.println("//////Choose///////");

            int actionNum = scanner.nextInt();
            switch (actionNum) {
                case 1:
                    handleAddAction();
                    break;
                case 2:
                    handleModifyAction();
                    break;
                case 3:
                    handleDeleteAction();
                    break;
                case 4:
                    handleViewAction();
                    break;
                case 5:
                    handleSerializeAction("test.xml");
                    break;
                case 6:
                    handleDeserializeAction("test.xml");
                    break;
                case 7:
                    System.out.println("Bye-bye!");
                    return;
                default:
                    Plugin plugin = plugins.get(actionNum);
                    if (plugin != null) {
                        plugin.execute(machines);
                        break;
                    }

                    System.out.println("Unsupported action");
                    break;
            }
        }
    }

    /**
     * Method adds Machine children object
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static void handleAddAction() throws IllegalAccessException, InstantiationException {
        System.out.println("\nWhich object would you like to add?");
        int num = 0;
        for (Class clz : machineClasses) {
            System.out.println(++num + ". " + clz.getSimpleName());
        }

        int clzNum = scanner.nextInt() - 1;
        Class clz = machineClasses.get(clzNum);

        Machine machine = (Machine) clz.newInstance();
        machines.add(machine);

        while (true) {
            System.out.println(
                    "\nWould you like to fill "
                            + clz.getSimpleName().toLowerCase()
                            + " properties? (y or n)");

            boolean edit = scanner.next().equals("y");
            if (!edit) {
                return;
            }

            modify(machine);
        }
    }

    /**
     * Method modifies Machine sub object
     * @throws IllegalAccessException
     */
    private static void handleModifyAction() throws IllegalAccessException {
        System.out.println("Which machine would you like to modify?");
        int num = 0;
        for (Machine machine : machines) {
            System.out.println(++num + ". " + machine);
        }

        int objNum = scanner.nextInt() - 1;

        Machine machine = machines.get(objNum);

        while (true) {
            System.out.println(
                    "\nWould you like to fill "
                            + machine.getClass().getSimpleName().toLowerCase()
                            + " properties? (y or n)");

            boolean edit = scanner.next().equals("y");
            if (!edit) {
                return;
            }

            modify(machine);
        }
    }

    /**
     * Method deletes machine object from machines list
     */
    private static void handleDeleteAction() {
        System.out.println("Which machine would you like to delete?");
        int num = 0;
        for (Machine machine : machines) {
            System.out.println(++num + ". " + machine);
        }

        int objNum = scanner.nextInt() - 1;
        machines.remove(objNum);
    }

    private static void handleViewAction() {
        if (machines.isEmpty()) {
            System.out.println("No machines :(");
            return;
        }
        System.out.println("\nMachines:");
        machines.forEach(System.out::println);
    }

    private static void handleSerializeAction(String fileName) throws IOException {
        serialize(new File(fileName), machines);
        System.out.println("Machines have been serialized");
    }

    private static void handleDeserializeAction(String fileName)
            throws IOException, ClassNotFoundException {
        List<Machine> deserialized = deserialize(new File(fileName));
        System.out.println("\nDeserialized machines:");
        deserialized.forEach(System.out::println);
    }

    /**
     * Method serializing machines to xml format to selected file
     * @param file selected file
     * @param machines machines for serialization
     * @throws IOException
     */
    private static void serialize(File file, List<Machine> machines) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(file, machines);
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
//            for (Machine machine : machines) {
//                oos.writeObject(machine);
//            }
//        }
    }

    /**
     * Method deserializing machines from selected file
     * @param file selected file
     * @return list of machines
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static List<Machine> deserialize(File file) throws IOException, ClassNotFoundException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(file, new TypeReference<List<Machine>>(){});
        //        List<Machine> machines = new ArrayList<>();
//
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//            try {
//                while (true) {
//                    machines.add((Machine) ois.readObject());
//                }
//            } catch (EOFException ex) {
//                return machines;
//            }
//        }
    }

    /**
     * Method shows all params of class and modifies param of object
     * @param machine selected machine subclass
     * @throws IllegalAccessException
     */
    private static void modify(Machine machine) throws IllegalAccessException {
        Class<? extends Machine> clz = machine.getClass();
        List<Field> fields =
                FieldUtils.getAllFieldsList(clz)
                        .stream()
                        .filter(field -> !Modifier.isStatic(field.getModifiers()))
                        .collect(Collectors.toList());

        fields.forEach(field -> field.setAccessible(true));

        System.out.println('\n' + clz.getSimpleName().toLowerCase() + " properties:");
        int num = 0;
        for (Field field : fields) {
            System.out.println(++num + ". " + field.getName());
        }

        System.out.println("\nWhich property would you like to edit?");

        int fieldNum = scanner.nextInt() - 1;
        Field field = fields.get(fieldNum);

        System.out.println("Please type value:");

        Class<?> type = field.getType();

        Object value;
        if (type.equals(Double.TYPE)) {
            value = scanner.nextDouble();
        } else if (type.equals(Integer.TYPE)) {
            value = scanner.nextInt();
        } else if (type.equals(Boolean.TYPE)) {
            value = scanner.nextBoolean();
        } else {
            value = scanner.next();
        }

        field.set(machine, value);

        System.out.println("\nProperty has been modified");
        System.out.println(machine);
    }
}
