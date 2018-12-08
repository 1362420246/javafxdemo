package com.qbk.fxdemo4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 页面跳转(创造新stage(页面))
 *
 * JavaFX多个页面（Stage）之间的切换的实现的基本思路：
  构建一个主Stage和多个副Stage页面（构建方式基本上是一样的）；
 在主Stage中设置某些控件的动作，这些动作通过触发副Stage页面的stage.show和stage.hide来实现副Stage的打开和关闭（或者将关闭动作绑定在副Stage的控件上）；
 主Stage和副Stage之间的数据交换，可以通过在主Stage或副Stage上储存相应的数据类，通过其进行数据交换（交换动作一般发生在副Stage打开或关闭的时间）；
 */
public class Main extends Application {

    public static Stage  stage ;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //从fxml获取布局和布局里面得控件
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        //把布局设置进窗口
        primaryStage.setScene(new Scene(root, 600, 400));
        //展示窗口
        primaryStage.show();
        stage = primaryStage ;
    }

    /**
     *  主函数加载
     */
    public static void main(String[] args) {
        launch(args);
    }




}
