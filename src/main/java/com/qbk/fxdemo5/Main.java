package com.qbk.fxdemo5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * HBox可以水平排列控件
 */
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }// create W  WW . y II  b a  I . CO M

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("");
        Group root = new Group();
        Scene scene = new Scene(root, 680, 480, Color.WHITE);

        HBox hbox = new HBox();
        Button button1 = new Button("Add               ");
        Button button2 = new Button("Remove   ");
        HBox.setHgrow(button1, Priority.ALWAYS);
        HBox.setHgrow(button2, Priority.ALWAYS);
        button1.setMaxWidth(Double.MAX_VALUE);
        button2.setMaxWidth(Double.MAX_VALUE);
        hbox.getChildren().addAll(button1, button2);

        hbox.setPrefWidth(680);

        root.getChildren().add(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}