package com.qbk.controller;



import com.qbk.config.ContextAware;
import com.qbk.constant.JDBCConstant;
import com.qbk.datasource.DynamicDataSource;
import com.qbk.entity.DbEntity;
import com.qbk.util.DbUtril;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

@Log4j2
@FXMLController
public class DbController implements Initializable {

    /**
     * 使用  @Autowired 可以第一次注册成功， 但是页面跳转 回来以后 Controller 又被实例化一次，就无法注入了
     */
    private DynamicDataSource dynamicDataSource = (DynamicDataSource) ContextAware.getApplicationContext().getBean("dynamicDataSource");

    @FXML
    private Button checkoutButton ,sureButton;

    @FXML
    private TextField ipText ,portText , usernameText ,dbNameText;

    @FXML
    private PasswordField passwordText ;

    @FXML
    private ChoiceBox<String> driveChoice;

    @FXML
    private void onCheckout( ActionEvent event){
        String ip = ipText.getText();
        String port = portText.getText();
        String username = usernameText.getText();
        String password = passwordText.getText();
        String dbName = dbNameText.getText();
        String drive = driveChoice.getValue();
        log.info("ip:【{}】，端口：【{}】，用户：【{}】，密码：【{}】，db：【{}】，驱动：【{}】",ip,port,username,password,dbName,drive);
        String driveClass = "" ;
        String url = "";
       if("MySql".equals(drive)){
           driveClass = "com.mysql.jdbc.Driver";
           url = String.format("jdbc:mysql://%s:%s/%s",ip,port,dbName);
       }else if ("Oracle".equals(drive)){
           driveClass = "oracle.jdbc.OracleDriver";
           url = String.format("jdbc:oracle:thin:@%s:%s:%s",ip,port,dbName);
       }else {
           Alert warning = new Alert(Alert.AlertType.WARNING,"请选择数据库类型");
           warning.showAndWait();
           return;
       }
       boolean result = dynamicDataSource.testDatasource(driveClass, url, username, password);
       if(result){
           Alert information = new Alert(Alert.AlertType.INFORMATION,"数据库校验成功");
           //设置标题，不设置默认标题为本地语言的information
           information.setTitle("校验");
           //设置头标题，默认标题为本地语言的information
           information.setHeaderText("成功");
           information.showAndWait();
       }else {
           Alert error  = new Alert(Alert.AlertType.ERROR ,"数据库校验失败");
           error .showAndWait();
       }
    }

    @FXML
    private void onSure( ActionEvent event){
        String ip = ipText.getText();
        String port = portText.getText();
        String username = usernameText.getText();
        String password = passwordText.getText();
        String dbName = dbNameText.getText();
        String drive = driveChoice.getValue();
        log.info("ip:【{}】，端口：【{}】，用户：【{}】，密码：【{}】，db：【{}】，驱动：【{}】",ip,port,username,password,dbName,drive);
        String driveClass = "" ;
        String url = "";
        if("MySql".equals(drive)){
            driveClass = "com.mysql.jdbc.Driver";
            url = String.format("jdbc:mysql://%s:%s/%s",ip,port,dbName);
        }else if ("Oracle".equals(drive)){
            driveClass = JDBCConstant.ORACLE_DRIVER;
            url = String.format("jdbc:oracle:thin:@%s:%s:%s",ip,port,dbName);
        }else {
            Alert warning = new Alert(Alert.AlertType.WARNING,"请选择数据库类型");
            warning.showAndWait();
            return;
        }
        boolean dataSource = dynamicDataSource.createDataSource("default", driveClass, url, username, password);
        if (dataSource) {
            DbEntity dbEntity = DbEntity.builder().ip(ip).port(port).drive(drive).db(dbName).username(username).password(password).build();
            DbUtril.write("jdbc.txt",dbEntity);
            Map<Object, Object> dynamicTargetDataSources = dynamicDataSource.getDynamicTargetDataSources();
            Object defaultTargetDataSource = dynamicTargetDataSources.get("default");
            dynamicDataSource.setDefaultTargetDataSource(defaultTargetDataSource);
            Alert information = new Alert(Alert.AlertType.INFORMATION,"数据库链接成功");
            //设置标题，不设置默认标题为本地语言的information
            information.setTitle("链接");
            //设置头标题，默认标题为本地语言的information
            information.setHeaderText("成功");
            information.showAndWait();
        }else {
            Alert error  = new Alert(Alert.AlertType.ERROR ,"数据库链接失败");
            error .showAndWait();
        }
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        new ChangeScene("MainView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("db初始化");
        driveChoice.setItems(FXCollections.observableArrayList("MySql","Oracle"));
       try{
           DbEntity dbEntity = (DbEntity) DbUtril.read("jdbc.txt");
           if(dbEntity != null){
               String ip = dbEntity.getIp();
               String port = dbEntity.getPort();
               String drive = dbEntity.getDrive();
               String db = dbEntity.getDb();
               String username = dbEntity.getUsername();
//        String password = dbEntity.getPassword();

               ipText.setText(ip);
               portText.setText(port);
               dbNameText.setText(db);
               usernameText.setText(username);
//        passwordText.setText(password);
           }
       }catch (Exception e){
           log.error(e);
       }



    }




}
