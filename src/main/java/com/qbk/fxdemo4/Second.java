package com.qbk.fxdemo4;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Second extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //从fxml里面加载布局
        Parent root = FXMLLoader.load(getClass().getResource("Second.fxml"));
        primaryStage.setTitle("Hello World");
        //设置窗口
        primaryStage.setScene(new Scene(root, 600, 400));
        //展示窗口
        primaryStage.show();

        //关闭方法
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("监听到第二个窗口关闭，并显示第一个窗口");
                //展示第一个窗口
                Main.stage.show();
            }
        });
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

    /**
     * 展示第二个页面
     */
    public void  showWindow()  {
        //创建 新的stage(窗口)
        Stage stage = new Stage();
        try {
            //启动 新的stage(窗口)
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
