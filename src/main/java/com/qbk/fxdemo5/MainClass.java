package com.qbk.fxdemo5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 分页控件
 */
public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) {

        //布局
        Group group = new Group();
        //场景
        Scene scene = new Scene(group, 640, 480, Color.WHITE);
        //窗体
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX之Pagination的使用");
        primaryStage.show();
        //分页控件
        Pagination mPagination = new Pagination(5, 0);
        mPagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                Label mLabel = new Label();
                mLabel.setTextFill(Color.web("#0076a3"));
                mLabel.setText("这是第" + param + "页");
                return mLabel;
            }
        });
        mPagination.prefHeight(200);
        mPagination.prefWidth(300);
        mPagination.setLayoutX(50);
        mPagination.setLayoutY(380);
        //把分页控件添加至布局
        group.getChildren().add(mPagination);
    }

    public static void main(String[] args) {
        launch(args);

    }
}
