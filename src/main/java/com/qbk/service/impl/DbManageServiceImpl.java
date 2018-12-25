package com.qbk.service.impl;


import com.qbk.constant.JDBCConstant;
import com.qbk.datasource.DynamicDataSource;
import com.qbk.entity.DbManage;
import com.qbk.exception.ServiceException;
import com.qbk.mapper.DbManageMapper;
import com.qbk.service.DbManageService;
import com.qbk.util.BaseResult;
import com.qbk.util.BaseResultGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author: quboka
 * @Date: 2018/12/14 09:44
 * @Description:
 */
@Log4j2
@Service("dbManageService")
@Transactional(rollbackFor = Exception.class ,isolation = Isolation.DEFAULT ,propagation = Propagation.REQUIRED)
public class DbManageServiceImpl implements DbManageService {

    @Autowired
    private DbManageMapper dbManageMapper ;

    @Autowired
    private DynamicDataSource dynamicDataSource ;

    @PostConstruct
    private void initLoad(){
        try {

            if(dynamicDataSource.getDynamicTargetDataSources().containsKey("default")){
                List<DbManage> list = dbManageMapper.selectSourceList();
                for (DbManage dataSource : list) {

                    String ip = dataSource.getIp();
                    String port = dataSource.getPort();
                    String username =dataSource.getUsername();
                    String password = dataSource.getPassword();
                    String dbName =dataSource.getDb();
                    String drive = dataSource.getDrive();
                    String key = dataSource.getKey();

                    String driveClass = "" ;
                    String url = "";
                    if("MySql".equals(drive)){
                        driveClass = "com.mysql.jdbc.Driver";
                        url = String.format("jdbc:mysql://%s:%s/%s",ip,port,dbName);
                    }else if ("Oracle".equals(drive)){
                        driveClass = JDBCConstant.ORACLE_DRIVER;
                        url = String.format("jdbc:oracle:thin:@%s:%s:%s",ip,port,dbName);
                    }
                    boolean result = dynamicDataSource.createDataSource(key,
                            driveClass,url, username ,password);
                    if (result){
                        log.info("数据源【{}】加载成功",dataSource.getKey());
                    }else {
                        log.error("数据源【{}】加载失败",dataSource.getKey());
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<DbManage> selectSourceList() {
        return dbManageMapper.selectSourceList();
    }

    @Override
    public BaseResult addDataSource(DbManage dbManage) {
        String ip = dbManage.getIp();
        String port = dbManage.getPort();
        String username =dbManage.getUsername();
        String password = dbManage.getPassword();
        String dbName =dbManage.getDb();
        String drive = dbManage.getDrive();
        String key = dbManage.getKey();
        try {

            String driveClass = "" ;
            String url = "";
            if("MySql".equals(drive)){
                driveClass = "com.mysql.jdbc.Driver";
                url = String.format("jdbc:mysql://%s:%s/%s",ip,port,dbName);
            }else if ("Oracle".equals(drive)){
                driveClass = "oracle.jdbc.OracleDriver";
                url = String.format("jdbc:oracle:thin:@%s:%s:%s",ip,port,dbName);
            }

            int result = dbManageMapper.insert(dbManage);
            if(result > 0){
                boolean dataSource = dynamicDataSource.createDataSource(key,
                        driveClass,url, username ,password);
                if(!dataSource){
                    log.error("添加至数据源路由失败");
                    //手动抛出异常
                    throw new ServiceException("添加至数据源路由失败");
                }
                return BaseResultGenerator.success();
            }else {
                log.error("插入数据源失败");
                return BaseResultGenerator.error("插入数据源失败");
            }
        }catch (Exception e){
            log.error("插入数据源异常",e);
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseResultGenerator.error("插入数据源异常");
        }
    }

    @Override
    public BaseResult deleteDbByList(List<String> list) {

        try{
            int result = dbManageMapper.deleteDbByList(list);
            if(result == 0){
                log.error("删除数据源失败");
                return BaseResultGenerator.error("删除数据源失败");
            }else {
                for (String key :list){
                    dynamicDataSource.delDatasources(key);
              }
            }
            log.info("删除数据源成功");
            return BaseResultGenerator.success("删除数据源成功");
        }catch (Exception e){
            log.error("删除数据源异常",e);
            return BaseResultGenerator.error("删除数据源异常");
        }
    }
}
