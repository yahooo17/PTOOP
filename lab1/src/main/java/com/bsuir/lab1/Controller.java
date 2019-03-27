package com.bsuir.lab1;

import com.bsuir.lab1.drawer.Drawer;
import com.bsuir.lab1.drawer.DrawerFactory;
import com.bsuir.lab1.entity.Point;
import com.bsuir.lab1.entity.Shape;
import com.bsuir.lab1.util.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class which handles events from view
 */
public class Controller {

    @FXML public AnchorPane base;
    @FXML public ComboBox<String> dropDown;
    @FXML public VBox inputContainer;
    @FXML public Button addButton;

    private Map<String, Class> shapeMap = new HashMap<>();
    private List<TextField> paramFields = new ArrayList<>();

    /**
     * Method for initializing view components
     */
    @FXML
    public void initialize() {

        List<Class> shapeClasses = Utils.getClasses("com.bsuir.lab1");
        //Getting all children classes of base class Shape
        shapeMap =
                shapeClasses
                        .stream()
                        .filter(clz -> !clz.isInterface())
                        .filter(clz -> clz.getSuperclass().equals(Shape.class))
                        .filter(clz -> !Modifier.isAbstract(clz.getModifiers()))
                        .collect(Collectors.toMap(Class::getSimpleName, clz -> clz));

        dropDown.getItems().addAll(shapeMap.keySet());

        //Handling dropdown actions
        dropDown.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Class<? extends Shape> shapeClass = shapeMap.get(dropDown.getValue());
                        Class[] params = getParams(shapeClass);

                        ObservableList<Node> children = inputContainer.getChildren();
                        children.clear();

                        generateParamFields(params, children);
                    }
                });
        //Handling add button actions
        addButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Shape shape = createShape(dropDown.getValue());
                        Drawer drawer = DrawerFactory.get(shape.getClass());
                        base.getChildren().add(drawer.draw(shape));
                    }
                });
    }

    /**
     * Method creates shapes
     * @param shapeName shape name
     * @return object of class Shape
     */
    private Shape createShape(String shapeName) {
        //Getting class by class name
        Class<? extends Shape> shapeClass = shapeMap.get(shapeName);
        //Getting array of params of selected class
        Class[] paramClasses = getParams(shapeClass);

        Constructor<? extends Shape> constructor;
        try {
            constructor = shapeClass.getConstructor(paramClasses);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        //Collect values from user
        Queue<Double> values =
                paramFields
                        .stream()
                        .map(TextInputControl::getText)
                        .map(Double::parseDouble)
                        .collect(Collectors.toCollection(LinkedList::new));

        Object[] params = new Object[paramClasses.length];

        //Defining types of entry data
        for (int i = 0; i < paramClasses.length; i++) {
            Class paramClass = paramClasses[i];
            if (paramClass.equals(Point.class)) {
                params[i] = new Point(values.poll(), values.poll());
            } else if (paramClass.equals(Double.TYPE)) {
                params[i] = values.poll();
            }
        }

        try {
            return constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method generates text fields for each shape for the application view
     * @param params params of the shape object
     * @param children list of children of shape
     */
    private void generateParamFields(Class[] params, ObservableList<Node> children) {
        paramFields = new ArrayList<>();
        for (Class param : params) {
            if (param.equals(Point.class)) {
                children.add(new Label("Point (x,y):"));
                TextField x = new TextField();
                TextField y = new TextField();
                children.add(x);
                children.add(y);
                paramFields.add(x);
                paramFields.add(y);
            } else if (param.equals(Double.TYPE)) {
                children.add(new Label("Line (x)"));
                TextField x = new TextField();
                children.add(x);
                paramFields.add(x);
            }
        }
    }

    /**
     * Getting params of class
     * @param shapeClass shape class
     * @return array of classes of params of shape class
     */
    private Class[] getParams(Class shapeClass) {
        try {
            Method getConstructParams = shapeClass.getDeclaredMethod("getConstructParams");
            getConstructParams.setAccessible(true);
            return (Class[]) getConstructParams.invoke(null);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
