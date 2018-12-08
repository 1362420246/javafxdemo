package com.qbk.fxdemo4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

/**
 * 在fmxl里面设置与此controller的绑定
 */
public class MainController implements Initializable {

    /**
     * 初始化方法
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            System.out.println("初始化第一个窗口");
    }

    /**
     *  button 点击事件
     */
    public void changeWindow() throws Exception {
        System.out.println("开启第二个窗口，并隐藏第一个窗口");
        //创建第二个页面的类
        Second second=new Second();
        //调用第二个页面的初始化方法
        second.showWindow();
        //隐藏第一个页面的 stage（窗口）
        Main.stage.hide();


    }

}
