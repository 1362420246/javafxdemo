package com.qbk.fxdemo3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;



/**
 *  最小化 TODO
 */
public class Minimize extends Application {

    @Override
    public void start(Stage primaryStage) {

        //4.控件
        Button btn = new Button("Say Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                btn.setText("Hello World, I am JavaFX!");
            }
        });

        //3.布局
        StackPane pane = new StackPane();
        pane.getChildren().add(btn);

        //2.场景
        Scene scene = new Scene(pane, 500, 500);

        // 1 .窗体
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World!");
        primaryStage.show();

//        primaryStage.setOnCloseRequest(arg0->primaryStage.hide());

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("监听到窗口关闭");
                primaryStage.hide();
//                primaryStage.setIconified(true);
            }
        });

        primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("setOnHidden");
            }
        });
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("setOnHiding");
            }
        });
        primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("setOnShowing");
            }
        });
        primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("setOnShown");
            }
        });




    }

    public static void main(String[] args) {
        // JavaFX中main函数必须需要调用launch函数
        launch(args);
    }



}