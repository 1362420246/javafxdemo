package com.qbk.controller;


import com.qbk.util.WindowsNet;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
@FXMLController
public class IpController implements Initializable {

    @FXML
    private Button checkoutButton ,sureButton;

    @FXML
    private TextField ipText ,subnetMasktText  ,gatewayText;

    @FXML
    private ChoiceBox<String> nameChoice;


    @FXML
    private void onSure( ActionEvent event){
       String ip = ipText.getText();
       String subnetMaskt = subnetMasktText.getText();
       String gateway = gatewayText.getText();
       String name = nameChoice.getValue();
       boolean result = WindowsNet.updateWindowsAddresses(name, ip, subnetMaskt, gateway);
       if(result){
           Alert information = new Alert(Alert.AlertType.INFORMATION,"修改ip成功");
           //设置标题，不设置默认标题为本地语言的information
           information.setTitle("ip");
           //设置头标题，默认标题为本地语言的information
           information.setHeaderText("成功");
           information.showAndWait();
       }else {
           Alert error  = new Alert(Alert.AlertType.ERROR ,"修改ip失败");
           error .showAndWait();
       }
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        new ChangeScene("MainView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("ip初始化");

        List<String> windowsAddresses = WindowsNet.getWindowsAddresses();

        nameChoice.setItems(FXCollections.observableArrayList(windowsAddresses));



    }




}
