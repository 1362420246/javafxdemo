package com.qbk.controller;

import com.qbk.util.CommandUtil;
import com.qbk.util.PropertiesUtil;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: quboka
 * @Date: 2018/12/17 15:29
 * @Description: 通用配置
 */
@Log4j2
@FXMLController
public class CommonConfigController implements Initializable{

    private PropertiesUtil util ;
    {
        String path = "" ;
        if(CommandUtil.systemCheck().startsWith("windows")){
            path =  CommandUtil.getUserPath()+"/config/";
        }else {
            path =  CommandUtil.getHomePath()+"/config/";
        }
        util = new PropertiesUtil( path ,"common.properties");
    }

    @FXML
    private TextField uploadText ;

    @FXML
    private void onSure( ActionEvent event){
        String uploadPath = uploadText.getText();
        if(StringUtils.isNotEmpty(uploadPath)){
            uploadPath = uploadPath.trim();
            boolean result = util.writeProperties("upload.path", uploadPath);
            if(result){
                Alert information = new Alert(Alert.AlertType.INFORMATION,"配置成功");
                //设置标题，不设置默认标题为本地语言的information
                information.setTitle("配置");
                //设置头标题，默认标题为本地语言的information
                information.setHeaderText("成功");
                information.showAndWait();
            }else {
                Alert error  = new Alert(Alert.AlertType.ERROR ,"配置失败");
                error .showAndWait();
            }
        }else {
            Alert error  = new Alert(Alert.AlertType.ERROR ,"不能为空");
            error .showAndWait();
        }

    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        new ChangeScene("MainView.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("通用配置初始化");
        String uploadPath = util.getProperty("upload.path");
        uploadText.setText(uploadPath);

    }
}
