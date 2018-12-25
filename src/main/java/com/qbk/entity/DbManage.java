package com.qbk.entity;

import javafx.beans.property.SimpleBooleanProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbManage {
    /**
     * null  * null
     */
    private String key;

    /**
     * null  * null
     */
    private String drive;

    /**
     * null  * null
     */
    private String ip;

    /**
     * null  * null
     */
    private String port;

    /**
     * null  * null
     */
    private String db;

    /**
     * null  * null
     */
    private String username;

    /**
     * null  * null
     */
    private String password;

    private boolean status;

    private SimpleBooleanProperty check = new SimpleBooleanProperty() ;
}