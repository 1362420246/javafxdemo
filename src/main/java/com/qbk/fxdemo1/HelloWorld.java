package com.qbk.fxdemo1;

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
 * 简单入门
 *
 *  开启启动项 ： -Dfile.encoding=GB18030
 * https://blog.csdn.net/theonegis/article/details/50086563
 * 一个JavaFX程序应该是这样子的：一个Stage（舞台），Stage中包含一个Scene（场景），一个Scene中可以包含多个Control（控件）。
 * 这里的Stage就相当于我们的一个Windows或者说窗体，然后窗体中是用来容纳内容的Scene，我们可以在Scene中添加我们的布局控件，UI控件形成我们最终的UI界面。
 * Java8内置了JavaFX
 * 一个普通的Java工程。然后新建一个类，该类需要继承javafx.application.Application类
 */
public class HelloWorld extends Application {



    /**
     * 所有JavaFX应用程序的主要入口点。
     * init方法返回后调用start方法，
     */
    @Override
    public void start(Stage primaryStage) {

        //4.控件
        /**
         * 首先我们创建一个Button，当我们点击Button的时候，改变Button的内容。
         *
         * Button btnHello = new Button("Hello");
         */
        Button btn = new Button("Say Hello World");
//        btn.setText("Say Hello World");

        /**
         * 设置btnHello按钮点击事件
         * 这里使用了Java8的Lambda表达式。setOnAction的参数为EventHandler<ActionEvent> value
         * EventHandler为一个接口，所以我们有三种方式实现EventHandler接口：
         * 1. 创建一个内部类
         * 2. 创建一个匿名类
         * 3. 使用Lambda表达式（适用于函数体不大的情况）
         *
         *  btnHello.setOnAction(event->{
         *     btnHello.setText("Hello World, I am JavaFX!");
         *  });
         */
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                btn.setText("Hello World, I am JavaFX!");


            }
        });

        //3.布局
        /**
         *  BorderPane是一个用于布局的Pane，BoerderPane将面板分割为上下左右中五部分。
         *  我们可以将UI控件放置在BorderPane的上下左右和中间。
         *  这里将将Button放置在中间。
         */
//        BorderPane pane = new BorderPane();
//        pane.setCenter(btn);
        StackPane pane = new StackPane();
        pane.getChildren().add(btn);

        //2.场景
        // 将pane加入到Scen中
        Scene scene = new Scene(pane, 500, 500);

        // 1 .窗体
        // 设置stage的scen，然后显示我们的stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World!");
        primaryStage.show();

    }

    public static void main(String[] args) {
        // JavaFX中main函数必须需要调用launch函数
        launch(args);
    }




}