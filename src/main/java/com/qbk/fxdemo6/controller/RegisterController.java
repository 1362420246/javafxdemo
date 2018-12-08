package com.qbk.fxdemo6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class RegisterController  {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repassword;

    @FXML
    private Label prompt;

    @FXML
    public void Register() throws Exception {
        // 测试
        System.out.println(username.getText() + "  " + password.getText());
        new ChangeScene("land.fxml");
    }

    @FXML
    public void ReturnLand() throws IOException {
        new ChangeScene("land.fxml");
    }


}
