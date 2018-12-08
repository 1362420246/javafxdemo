package com.qbk.fxdemo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 用户登陆 注册demo
 */
public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage myStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/land.fxml"));

        myStage.setTitle("land system");

        stage = myStage;

        myStage.setScene(new Scene(root));
        myStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}