package com.qbk.controller;


import com.qbk.JavafxDemoApplication;
import com.qbk.config.ContextAware;
import com.qbk.datasource.DynamicDataSource;
import com.qbk.util.WindowsNet;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

@Log4j2
@FXMLController
public class MainController implements Initializable {

    /**
     * 使用  @Autowired 可以第一次注册成功， 但是页面跳转 回来以后 Controller 又被实例化一次，就无法注入了
     */
    private DynamicDataSource dynamicDataSource = (DynamicDataSource) ContextAware.getApplicationContext().getBean("dynamicDataSource");

    @FXML
    private Label dbTest ;

    /**
     *  数据源配置
     */
    @FXML
    private void onConfig( ActionEvent event) throws IOException {
        new ChangeScene("Db.fxml");
    }
    /**
     *  ip配置
     */
    @FXML
    private void onIpConfig( ActionEvent event) throws IOException {
        new ChangeScene("Ip.fxml");
    }
    /**
     *  开机启动项
     */
    @FXML
    private void regAdd ( ActionEvent event) throws IOException {
         boolean result = WindowsNet.regAdd();
        if(result){
            Alert information = new Alert(Alert.AlertType.INFORMATION,"加入开启启动项成功");
            //设置标题，不设置默认标题为本地语言的information
            information.setTitle("开启启动项");
            //设置头标题，默认标题为本地语言的information
            information.setHeaderText("成功");
            information.showAndWait();
        }else {
            Alert error  = new Alert(Alert.AlertType.ERROR ,"加入开启启动项失败");
            error .showAndWait();
        }
    }

    /**
     *  主页（打开网页）
     */
    @FXML
    private void goHomepage( ActionEvent event) throws IOException {
        WindowsNet.openWebpage("http://localhost:"+ JavafxDemoApplication.port);
    }

    /**
     *  数据库管理
     */
    @FXML
    private void goDbManage( ActionEvent event) throws IOException {
        new ChangeScene("DbManageView.fxml");
    }
    @FXML
    private void goCommonConfig( ActionEvent event) throws IOException {
        new ChangeScene("CommonConfigView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("main初始化");
        Map<Object, Object> dynamicTargetDataSources = dynamicDataSource.getDynamicTargetDataSources();
        if(dynamicTargetDataSources.containsKey("default")){
            dbTest.setText("初始化数据库成功");
        }else {
            dbTest.setText("初始化数据库失败");
        }
    }




}
