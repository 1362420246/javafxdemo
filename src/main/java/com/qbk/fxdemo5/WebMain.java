package com.qbk.fxdemo5;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * @Author: quboka
 * @Date: 2019/1/8 15:46
 * @Description: 内嵌web
 */
public class WebMain extends Application {
    @Override
    public void start(final Stage stage) {
        stage.setWidth(800);
        stage.setHeight(900);
        Scene scene = new Scene(new Group());


        //WebView节点是一个小型浏览器
        final WebView browser = new WebView();
        /**
         * JavaFX提供了一个能够加载HTML5内容的非GUI组件，称为WebEngine API(javafx.scene.web.WebEngine)。
         这个API基本上是一个WebEngine类的对象实例，用于加载包含HTML5内容的文件。
         要加载的HTML5文件可以位于本地文件系统，Web服务器或JAR文件中。
         使用Web引擎对象加载文件时，将使用后台线程来加载Web内容，以便它不会阻止JavaFX应用程序线程。
         */
        final WebEngine webEngine = browser.getEngine();
        //通过使用WebEngine的load()方法从URL加载Web内容
        webEngine.load("http://www.baidu.com");

        //要监视或确定工作线程是否已完成javafx.beans.value.ChangeListener添加到state属性。
        webEngine.getLoadWorker()
                .stateProperty()
                .addListener(
                        new ChangeListener<State>() {
                            @Override
                            public void changed(ObservableValue ov, State oldState, State newState) {
                                if (newState == Worker.State.SUCCEEDED) {
                                    stage.setTitle(webEngine.getLocation());
                                }
                            }
                });

        /**
         *
         * 当在JavaFX WebView节点中执行代码时，不会弹出本机对话框窗口。但是，OnAlert事件作为javafx.scene.web.WebEvent对象引发。
         我们可以处理这些事件。要设置处理程序，请使用带有类型为WebEvent的入站参数的setOnAlert()方法。

         * 下表显示了从javafx.scene.web.WebEngine WebEvents和Properties触发它们的事件和操作。
         SetOn方法	方法属性	描述
         setOnAlert()	onAlertProperty()	处理JavaScript警报方法
         setOnError()	onErrorProperty()	WebEngine错误处理程序
         setOnResized()	onResizedProperty()	JavaScript调整大小处理程序
         setOnStatusChanged()	onStatusChanged()	JavaScript状态处理程序
         setOnVisibilityChanged()	onVisibilityChangedProperty()	JavaScript窗口可见处理程序
         setConfirmHandler()	confirmHandlerProperty()	JavaScript确认窗口
         */
        browser.getEngine().setOnAlert((WebEvent<String> wEvent) -> {
            System.out.println("Alert Event  -  Message:  " + wEvent.getData());
        });

        //管理网络历史记录
        final WebHistory history = webEngine.getHistory();
        history.getEntries().addListener(
                new ListChangeListener<WebHistory.Entry>() {
                 @Override
                 public void onChanged(Change<? extends WebHistory.Entry> c) {
                     c.next();
                     for (WebHistory.Entry e : c.getRemoved()) {
                         System.out.println(e.getUrl());
                     }
                     for (WebHistory.Entry e : c.getAddedSubList()) {
                         System.out.println(e.getUrl());
                     }
                 }
             }
        );


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);

        scene.setRoot(scrollPane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
