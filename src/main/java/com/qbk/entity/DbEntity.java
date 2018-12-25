package com.qbk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: quboka
 * @Date: 2018/12/7 17:53
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbEntity implements Serializable {

    private String ip ;
    private String port ;
    private String drive ;
    private String db ;
    private String username ;
    private String password ;
}
