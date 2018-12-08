package com.qbk.fxdemo6.controller;

import com.qbk.fxdemo6.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.io.IOException;

/**
 *  创建Scene(场景)切换页面
 *  一个stage(页面)切换不同Scene(场景),不同Scene加载不同fxml(布局和组件)
 */
public class ChangeScene {

    public  ChangeScene(String fxml) throws IOException {
        //fxml 布局 路径
        String src = "/com/qbk/fxdemo6/view/" + fxml;
        //加载布局
        Parent root = FXMLLoader.load(getClass().getResource(src));
        Main.stage.setTitle("land system");
        //切换不同Scene(场景)
        Main.stage.setScene(new Scene(root));
        //展示
        Main.stage.show();
    }
}
