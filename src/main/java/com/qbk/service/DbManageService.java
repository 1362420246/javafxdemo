package com.qbk.service;



import com.qbk.entity.DbManage;
import com.qbk.util.BaseResult;

import java.util.List;

/**
 * @Author: quboka
 * @Date: 2018/12/14 09:43
 * @Description:
 */
public interface DbManageService {
    List<DbManage> selectSourceList();

    BaseResult addDataSource(DbManage dbManage);

    BaseResult deleteDbByList(List<String> list);
}
