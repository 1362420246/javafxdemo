package com.qbk.controller;


import com.qbk.config.ContextAware;
import com.qbk.entity.DbManage;
import com.qbk.service.DbManageService;
import com.qbk.util.BaseResult;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Log4j2
@FXMLController
public class PopupController implements Initializable {

    /**
     * 使用  @Autowired 可以第一次注册成功， 但是页面跳转 回来以后 Controller 又被实例化一次，就无法注入了
     */
    private DbManageService dbManageService = (DbManageService) ContextAware.getApplicationContext().getBean("dbManageService") ;

    @FXML
    private TextField ipText ,portText , usernameText ,dbNameText ,keyText;

    @FXML
    private PasswordField passwordText ;

    @FXML
    private ChoiceBox<String> driveChoice;

    @FXML
    private void onSure( ActionEvent event) throws IOException {
        String ip = ipText.getText();
        String port = portText.getText();
        String username = usernameText.getText();
        String password = passwordText.getText();
        String dbName = dbNameText.getText();
        String drive = driveChoice.getValue();
        String key = keyText.getText();

        BaseResult baseResult =dbManageService.addDataSource(DbManage.builder().key(key).
                drive(drive).db(dbName).ip(ip).port(port).username(username).password(password).build());
        if (baseResult.isCode()) {
            Stage stage=(Stage) getWindow(event);
            stage.close();
            new ChangeScene("DbManageView.fxml");
        }else {
            Alert error  = new Alert(Alert.AlertType.ERROR ,"数据库添加失败");
            error .showAndWait();
        }
    }
    /**
     * 获取控件元素的窗口对象
     * @param event
     * @return
     */
    public static Window getWindow(ActionEvent event){
        return((Node)event.getSource()).getScene().getWindow();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("弹窗初始化");
        driveChoice.setItems(FXCollections.observableArrayList("MySql","Oracle"));
    }

}
