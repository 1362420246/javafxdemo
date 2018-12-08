package com.qbk.fxdemo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 *  用fxml布局
 *
 * https://blog.csdn.net/theonegis/article/details/50181339
 * 总结一下：
 JavaFX程序中我们可以使用FXML文件编写前台界面，使用FXMLLoader类将FXML文件绑定到主程序。
 使用一个Controller类和@FXML注解将操作的逻辑绑定到FXML文件中的界面元素。
 在FXML文件中使用fx:id属性声明界面元素的id，在Controller类中以相同的名称定义该元素。onAction属性值给定以#号开头的事件处理函数名称，在Controller类中实现该函数。
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // 这里的root从FXML文件中加载进行初始化，这里FXMLLoader类用于加载FXML文件
            URL url = getClass().getResource("MainPanel.fxml");
            System.out.println(url);
            //1.获取布局
            BorderPane root = (BorderPane)FXMLLoader.load(url);
            //2.创建场景  并装载布局
            Scene scene = new Scene(root, 500, 500);
            //3.设置窗口
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hello World");
            //4展示窗口
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

