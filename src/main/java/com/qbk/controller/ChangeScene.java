package com.qbk.controller;

import com.qbk.JavafxDemoApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class ChangeScene {

    public ChangeScene(String fxml) throws IOException {
        //fxml 布局 路径
        String src = "/view/" + fxml;
        //加载布局
        Parent root = FXMLLoader.load(getClass().getResource(src));
        JavafxDemoApplication.stage.setTitle("land system");
        //切换不同Scene(场景)
        JavafxDemoApplication.stage.setScene(new Scene(root));
        //展示
        JavafxDemoApplication.stage.setTitle("中间件");
        JavafxDemoApplication.stage.show();
    }
}
