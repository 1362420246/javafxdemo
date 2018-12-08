package com.qbk.fxdemo6.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


public class LandController implements Initializable {

    @FXML
    private Button onload ;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label prompt;

    @FXML
    public void loadIn() throws Exception {
        System.out.println(username.getText() + " " + password.getText());
        new ChangeScene("mainScene.fxml");
    }

    @FXML
    public void toRegister() throws IOException {
        new ChangeScene("register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
