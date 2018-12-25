package com.qbk.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.qbk.constant.JDBCConstant;
import com.qbk.datasource.DynamicDataSource;
import com.qbk.entity.DbEntity;
import com.qbk.util.DbUtril;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.HashMap;

/**
 * @Author: quboka
 * @Date: 2018/12/3 16:26
 * @Description:  数据源配置
 */
@Log4j2
@Configuration
@MapperScan(basePackages = { "com.qbk.mapper" })
public class DataSourceConfig {

    /**
     *  配置数数据路由（也是数据源）
     */
    @Primary //表示默认
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(){
        //创建数据路由对象
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> dataSourceshMap = new HashMap<>(1);

        //初始化数据源
        try {
            DbEntity dbEntity = (DbEntity) DbUtril.read("jdbc.txt");
            if (dbEntity != null){
                String driveClass = "" ;
                String url = "" ;
                if("MySql".equals(dbEntity.getDrive())){
                    driveClass = "com.mysql.jdbc.Driver";
                    url = String.format("jdbc:mysql://%s:%s/%s",dbEntity.getIp(),dbEntity.getPort(),dbEntity.getDb());
                }else if ("Oracle".equals(dbEntity.getDrive())){
                    driveClass = JDBCConstant.ORACLE_DRIVER;
                    url = String.format("jdbc:oracle:thin:@%s:%s:%s",dbEntity.getIp(),dbEntity.getPort(),dbEntity.getDb());
                }
                log.info("driveClass：【{}】，url:【{}】,username:【{}】,password:【{}】",driveClass,url,dbEntity.getUsername(), dbEntity.getPassword());
                // 排除连接不上的错误
                Class.forName(driveClass);
                // 相当于连接数据库
                DriverManager.getConnection(url,dbEntity.getUsername(), dbEntity.getPassword());
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setName("default");
                druidDataSource.setDriverClassName(driveClass);
                druidDataSource.setUrl(url);
                druidDataSource.setUsername(dbEntity.getUsername());
                druidDataSource.setPassword( dbEntity.getPassword());
                druidDataSource.setMaxWait(60000);
                druidDataSource.setFilters("stat");
                DataSource createDataSource = (DataSource) druidDataSource;
                druidDataSource.init();

                dataSourceshMap.put("default",createDataSource);
                log.info("初始化数据源成功");
            }else {
                log.info("初始化数据源失败,缺少配置");
            }
        } catch (Exception e) {
            log.error("初始化数据源失败："+e);
        }
        //设置数据源集合
        dynamicDataSource.setTargetDataSources(dataSourceshMap);
        return dynamicDataSource ;
    }




}
