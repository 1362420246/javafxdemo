package com.qbk.fxdemo5;

import javafx.beans.property.SimpleStringProperty;

/**
 * @Author: quboka
 * @Date: 2018/12/13 14:56
 * @Description:   JavaFX 的属性
 *
 * https://zhuanlan.zhihu.com/p/30882888
 * 为了实现基于JavaBean的属性、绑定以及事件机制，JavaFX对JavaBean进行了扩展，JavaBean不再是POJO，显得更加“重量级”一些。
 * JavaFX为Java通用包装类提供了通用的属性包装类，以实现事件监听、数据绑定等功能。
 * String   StringProperty
 * Integer  IntegerProperty
 * Boolean  BooleanProperty
 * Object   ObjectProperty
 * List     ObservableList
 */
public class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;

    Person(String fName, String lName) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String fName) {
        lastName.set(fName);
    }
}
