package com.qbk.fxdemo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @FXML注解用于说明该变量或者方法可以在FXML文件中进行访问。官方对@FXML的说明为：
 * Annotation that tags a class or member as accessible to markup。
 * handleButtonAction()方法在FXML文件中说明，为Button的onAction的属性值，我们在handleButtonAction()方法中进行点击按钮的处理逻辑。
 */
public class MainPaneController {
   //这里的Button对象有需要加@FXML注解，然后变量的名称为你刚才在FXML文件中声明的Button的id属性
    @FXML
    private Button btnHello;

    //这里的handleButtonAction方法为我们在FXML文件中声明的onAction的处理函数
    @FXML
    protected void handleButtonAction(ActionEvent event) {
        btnHello.setText("Hello World, I am JavaFX!");
    }

}
