package com.qbk.fxdemo5;

import com.qbk.controller.DbManageController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * @Author: quboka
 * @Date: 2019/1/2 14:15
 * @Description: 自定义弹窗
 */
public class PopupMain extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("弹窗");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage stage=(Stage) getWindow(event);
                startSub(stage);
            }
        });
        StackPane pane = new StackPane();
        pane.getChildren().add(btn);
        Scene scene = new Scene(pane, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.setTitle("pop-up windows");
        primaryStage.show();

    }



    /**
     * 获取控件元素的窗口对象
     * @param event
     * @return
     */
    public static Window getWindow(ActionEvent event){
        return((Node)event.getSource()).getScene().getWindow();
    }

    /**
     * 启动子窗口
     * @throws IOException
     */
    public static void startSub(Window owner)  {
        Stage stage=new Stage();
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane,320,80);
        stage.setScene(scene);
        stage.setTitle("子窗口");
        stage.initModality(Modality.WINDOW_MODAL);
        //设置父窗口
        stage.initOwner(owner);
        stage.show();
    }



}
