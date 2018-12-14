package com.qbk.fxdemo5;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: quboka
 * @Date: 2018/12/13 14:40
 * @Description: 表单控件
 *https://www.w3cschool.cn/java/javafx-tableview.html
 * 我们可以使用来自JavaFX API的TableView，TableColumn和TableCell类以表格形式表示数据。
    通过实现数据模型和应用单元工厂来填充表中的数据。
    表类可以按列排序数据，并在必要时调整列大小。
 *
 */
public class TableMain extends Application {

    /**
     * 装数据的list
     */
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(new Person("A", "B"),new Person("k","k"));

    /**
     * 水平排列控件
     */
    final HBox hb = new HBox();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //表控件是通过实例化TableView类创建的。
        TableView table = new TableView();
        table.setEditable(true);

        //然后使用TableColumn类创建三个列。
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        //设置 表单列 展示的数据字段
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        //设置 表单列 展示的数据字段
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        TableColumn emailCol = new TableColumn("Email");

        //多选框列
        TableColumn checkBoxColumn = new TableColumn("勾选");
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));


        //TableView类的getColumns方法将创建的列添加到表中。
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,checkBoxColumn);

        //初始化数据
        table.setItems(data);


        //我们可以通过调用setVisible方法隐藏列。
        //emailCol.setVisible(false);


        //使用JavaFX表视图，我们可以轻松创建嵌套列。
        // 假设我们要将两个子列添加到地址列。
        TableColumn cityCol = new TableColumn("City");
        TableColumn stateCol = new TableColumn("State");
        //然后我们将新创建的列添加到地址列。
        emailCol.getColumns().addAll(cityCol, stateCol);


        /*
        添加新行
        以下代码显示如何向表视图中添加数据。 创建JavaFX JavaBean以保存单个行的值。 表中的每一行代表一个名字为姓氏的人。
         JavaFX JavaBean称为Person，它有两个字段，名字和姓氏。 Person为这两个值提供了可绑定的属性。
        在UI逻辑中，它使用ObservableList来保存表视图的值。 ObservableList中的每个元素都是一个Person对象。
        在按钮事件处理程序中，它创建一个新的具有硬编码的名字和姓氏的人，然后添加到ObservableList。
         */
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            //往数据的list 里面添加 数据对象
            data.add(new Person("Z","X"));
        });

        //水平布局
        hb.getChildren().addAll(addButton);
        //间距
        hb.setSpacing(3);

        //垂直布局
        final VBox vbox = new VBox();
        //间距
        vbox.setSpacing(5);
        //内边框
        vbox.setPadding(new Insets(10, 0, 0, 10));
        //垂直布局
        vbox.getChildren().addAll(table, hb);

        //添加场景
        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);


        primaryStage.setWidth(450);
        primaryStage.setHeight(550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
