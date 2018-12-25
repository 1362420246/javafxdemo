package com.qbk.controller;



import com.qbk.config.ContextAware;
import com.qbk.datasource.DynamicDataSource;
import com.qbk.entity.DbManage;
import com.qbk.service.DbManageService;
import com.qbk.util.BaseResult;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
@FXMLController
public class DbManageController  implements Initializable {

    /**
     * 使用  @Autowired 可以第一次注册成功， 但是页面跳转 回来以后 Controller 又被实例化一次，就无法注入了
     */
    private DbManageService dbManageService = (DbManageService) ContextAware.getApplicationContext().getBean("dbManageService") ;

    private DynamicDataSource dynamicDataSource = (DynamicDataSource) ContextAware.getApplicationContext().getBean("dynamicDataSource");

    private ObservableList<DbManage> data = FXCollections.observableArrayList();

    @FXML
    private TableView dbTable;

    @FXML
    private TableColumn key ,drive ,ip ,port ,db ,username , password ,status ,checkBoxColumn;

    @FXML
    private Button addButton ;

    @FXML
    private void goback(ActionEvent event) throws IOException {
        new ChangeScene("MainView.fxml");
    }

    /**
     * 删除
     * @param event
     * @throws IOException
     */
    @FXML
    private void delete(ActionEvent event) throws IOException {
        List<String> list = new ArrayList<>();
        List<String> removeList = new ArrayList<>();
        for (DbManage dbManage: data) {
            System.out.println(dbManage.getCheck().get());
            if(dbManage.getCheck().get()){
                //被选中的
                list.add(dbManage.getKey());
            }
        }
        if(list.size() > 0){
            final BaseResult baseResult = dbManageService.deleteDbByList(list);
            if(baseResult.isCode()){
                Alert information = new Alert(Alert.AlertType.INFORMATION,"删除数据源成功");
                //设置标题，不设置默认标题为本地语言的information
                information.setTitle("删除");
                //设置头标题，默认标题为本地语言的information
                information.setHeaderText("成功");
                information.showAndWait();

                new ChangeScene("DbManageView.fxml");
            }else {
                Alert error  = new Alert(Alert.AlertType.ERROR ,"删除数据源失败");
                error .showAndWait();
            }
        }else {
            Alert warning = new Alert(Alert.AlertType.WARNING,"请勾选");
            warning.showAndWait();
        }

    }

    /**
     *  新增
     * @param event
     * @throws IOException
     */
    @FXML
    private void addDb (ActionEvent event) throws IOException {
        Stage stage=(Stage) getWindow(event);
        stage.setTitle("新增数据库");
        startSub(stage);
//        startSub(AirmanAmsApplication.stage);
    }

    /**
     * 获取控件元素的窗口对象
     * @param event
     * @return
     */
    public static Window getWindow(ActionEvent event){
        return((Node)event.getSource()).getScene().getWindow();
    }

    /**
     * 启动子窗口
     * @throws IOException
     */
    public static void startSub(Window owner) throws IOException{
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(DbManageController.class.getResource("/view/Popup.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        //设置父窗口
        stage.initOwner(owner);
        stage.show();
    }


    /**
     * 获取menuItem控件的窗口对象
     * @param event
     * @return
     */
    public static Window getWindowFromMenu(ActionEvent event){
        MenuItem menuItem=((MenuItem)event.getSource());
        //转换成ContextMenu
        ContextMenu cm=menuItem.getParentPopup();
        return cm.getOwnerWindow();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("数据库管理初始化");
        try {
            if (dynamicDataSource.getDynamicTargetDataSources().containsKey("default")){

                List<DbManage> list = dbManageService.selectSourceList();
                for (DbManage db :
                        list) {
                    if (dynamicDataSource.getDynamicTargetDataSources().containsKey(db.getKey())) {
                        db.setStatus(true);
                    }
                }
                //可编辑
                dbTable.setEditable(true);
                //设置 表单列 展示的数据字段
                key.setCellValueFactory(new PropertyValueFactory<>("key"));
                drive.setCellValueFactory(new PropertyValueFactory<>("drive"));
                ip.setCellValueFactory(new PropertyValueFactory<>("ip"));
                port.setCellValueFactory(new PropertyValueFactory<>("port"));
                db.setCellValueFactory(new PropertyValueFactory<>("db"));
                username.setCellValueFactory(new PropertyValueFactory<>("username"));
                password.setCellValueFactory(new PropertyValueFactory<>("password"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));

                //设置多选框
                checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer param) {
                        System.out.println(param );
                        return list.get(param).getCheck();
                    }
                }));

                //把list 转成 属性集合
                data.addAll(list);

                //初始化数据
                dbTable.setItems(data);


            }else {
                addButton.setDisable(true);
                Alert error  = new Alert(Alert.AlertType.ERROR ,"请先设置默认数据源");
                error .showAndWait();

            }
        }catch (Exception e){
            e.printStackTrace();
            addButton.setDisable(true);
            Alert error  = new Alert(Alert.AlertType.ERROR ,"默认数据库配置错误");
            error .showAndWait();
        }

    }
}
