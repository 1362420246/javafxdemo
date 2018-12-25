package com.qbk.util;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 *  屬性文件读写工具
 */
@Log4j2
public class PropertiesUtil {

    private Properties props;
    private URI uri ;

    public PropertiesUtil(String fileName){
        readProperties(fileName);
    }

    public PropertiesUtil(String path,String fileName){
        readProperties(path ,fileName);
    }

    private void readProperties(String fileName) {
        try {
            props = new Properties();
            InputStream fis =getClass().getResourceAsStream(fileName);
            props.load(fis);
            uri = this.getClass().getResource(fileName).toURI();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void readProperties(String path,String fileName) {
        try {
            props = new Properties();
            InputStream fis = new FileInputStream(new File(path,fileName));
            props.load(fis);
            uri = new File(path,fileName).toURI();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 获取某个属性
     */
    public String getProperty(String key){
        return props.getProperty(key);
    }
    /**
     * 获取所有属性，返回一个map,不常用
     * 可以试试props.putAll(t)
     */
    public Map getAllProperty(){
        Map map=new HashMap();
        Enumeration enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }
    /**
     * 在控制台上打印出所有属性，调试时用。
     */
    public void printProperties(){
        props.list(System.out);
    }
    /**
     * 写入properties信息
     */
    public boolean writeProperties(String key, String value) {
        try {
            OutputStream fos = new FileOutputStream(new File(uri));
            props.setProperty(key, value);
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "『comments』Update key：" + key);

            return true ;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false ;
        }
    }
    public static void main(String[] args) {
       try{
           String path = CommandUtil.getHomePath()+"/config/";
           PropertiesUtil util=new PropertiesUtil(path,"common.properties");
           final String key = util.getProperty("key");
           System.out.println(key);
           util.writeProperties("key", "qqqqqq");
       }catch (Exception e){
            System.out.println("异常");
       }
    }

}
