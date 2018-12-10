package com.qbk.fxdemo3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


/**
 *  最小化demo
 *  开启启动项 ： -Dfile.encoding=GB18030
 *
 *有以下几点需要清楚：
  1. stage.hide() 与 stage.close() 等价
 2. 如果要阻止Fx窗口在按下关闭按钮后退出，的确需要setOnCloseRequest，可是在handler函数中需要调用event.consume()来阻止事件进一步传递，这样才能真正阻止Window Close事件的默认处理。
 3. 默认情况下，Fx运行时会在最后一个stage close(或hide)后自动关闭，即自动调用Application.stop()，除非通过Platform.setImplicitExit(false)取消这个默认行为。
 这样，即使所有Fx窗口关闭（或隐藏），Fx运行时还在正常运行，可以再次显示原来的窗口或打开新的窗口。
 */
public class Minimize extends Application {

    //托盘图标
    private TrayIcon trayIcon;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //使用托盘
        enableTray(stage);

        Button btn = new Button("Say Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                btn.setText("Hello World, I am JavaFX!");
            }
        });
        StackPane pane = new StackPane();
        pane.getChildren().add(btn);
        stage.setScene(new Scene(pane, 500, 500));
        stage.setTitle("最小化demo");
        //窗口关闭事件
        stage.setOnCloseRequest(arg0->stage.hide());
        //多次使用显示和隐藏设置false
        Platform.setImplicitExit(false);
        stage.show();
    }

    /**
     *  使用托盘
     */
    private void enableTray(final Stage stage) {

        PopupMenu popupMenu = new PopupMenu();
        java.awt.MenuItem openItem = new java.awt.MenuItem("显示");
        java.awt.MenuItem hideItem = new java.awt.MenuItem("最小化");
        java.awt.MenuItem quitItem = new java.awt.MenuItem("退出");

        ActionListener acl = e -> {
            MenuItem item = (MenuItem) e.getSource();
            //多次使用显示和隐藏设置false
            Platform.setImplicitExit(false);

            if (item.getLabel().equals("退出")) {
                SystemTray.getSystemTray().remove(trayIcon);
                Platform.exit();
                return;
            }
            if (item.getLabel().equals("显示")) {
                Platform.runLater(() -> stage.show());
            }
            if (item.getLabel().equals("最小化")) {
                Platform.runLater(() -> stage.hide());
            }

        };

        //双击事件方法
        MouseListener sj = new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                //多次使用显示和隐藏设置false
                Platform.setImplicitExit(false);
                if (e.getClickCount() == 2) {
                    if (stage.isShowing()) {
                        Platform.runLater(() -> stage.hide());
                    }else{
                        Platform.runLater(() -> stage.show());
                    }
                }
            }
        };

        openItem.addActionListener(acl);
        quitItem.addActionListener(acl);
        hideItem.addActionListener(acl);

        popupMenu.add(openItem);
        popupMenu.add(hideItem);
        popupMenu.add(quitItem);

        try {
            //添加系统托盘图标
            SystemTray tray = SystemTray.getSystemTray();
            BufferedImage image = ImageIO.read(Minimize.class
                    .getResourceAsStream("station.jpg"));
            trayIcon = new TrayIcon(image, "自动备份工具", popupMenu);
            trayIcon.setToolTip("自动备份工具");
            tray.add(trayIcon);
            trayIcon.addMouseListener(sj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}