package com.qbk.fxdemo6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController {
    @FXML
    private Button returnLand;

    @FXML
    public void ReturnLand() throws Exception {
        new ChangeScene("land.fxml");
    }
}
