package com.qbk.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: quboka
 * @Date: 2018/12/5 16:29
 * @Description:
 */
@FXMLController
public class TestController  implements Initializable {

    @FXML
    private Button btnHello;

    @FXML
    protected void handleButtonAction(ActionEvent event) {
        System.out.println("Hello World, I am JavaFX!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
