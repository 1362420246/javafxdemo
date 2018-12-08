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
 * @Date: 2018/12/5 13:06
 * @Description:
 */

@FXMLController
public class FxContrller implements Initializable {

    @FXML
    private Button btnHello;

    @FXML
    protected void handleButtonAction(ActionEvent event) {
        System.out.println("Hello World, I am JavaFX!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     System.out.println(location);
     System.out.println(resources);


    }
}
