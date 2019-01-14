package com.qbk.fxdemo5;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @Author: quboka
 * @Date: 2019/1/11 17:26
 * @Description:  选择框
 */
public class ChoiceBoxMain  extends Application {

    ObservableList cursors = FXCollections.observableArrayList(
            Cursor.DEFAULT,
            Cursor.CROSSHAIR,
            Cursor.WAIT,
            Cursor.TEXT,
            Cursor.HAND,
            Cursor.MOVE,
            Cursor.N_RESIZE,
            Cursor.NE_RESIZE,
            Cursor.E_RESIZE,
            Cursor.SE_RESIZE,
            Cursor.S_RESIZE,
            Cursor.SW_RESIZE,
            Cursor.W_RESIZE,
            Cursor.NW_RESIZE,
            Cursor.NONE
    );
    @Override
    public void start(Stage stage) {
        ChoiceBox choiceBoxRef = new ChoiceBox<String>(FXCollections.observableArrayList(cursors));

        VBox box = new VBox();
        box.getChildren().add(choiceBoxRef);
        final Scene scene = new Scene(box,300, 250);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
        scene.cursorProperty().bind(choiceBoxRef.getSelectionModel()
                .selectedItemProperty());

        choiceBoxRef.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(observable);
                System.out.println(observable.getValue());
                System.out.println(cursors.get((int)newValue));
                System.out.println(oldValue);
                System.out.println(newValue);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
