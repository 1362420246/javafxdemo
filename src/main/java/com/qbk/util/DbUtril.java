package com.qbk.util;


import lombok.extern.log4j.Log4j2;

import java.io.*;

/**
 * @Author: quboka
 * @Date: 2018/12/7 17:55
 * @Description: 对象流读写文件工具
 */
@Log4j2
public class DbUtril {

    private static String path;
    static {
        if(CommandUtil.systemCheck().startsWith("windows")){
            path =  CommandUtil.getUserPath()+"/config/";
        }else {
            path =  CommandUtil.getHomePath()+"/config/";
        }

    }

    public static boolean write(String fileName ,Object o){
        OutputStream  os = null ;
        ObjectOutputStream oos =null ;
       try {
           File files = new File(path );
           if ( !files.exists() ){
               files.mkdirs();
           }
           File file = new File(files,fileName);
            os = new FileOutputStream(file);
            oos = new ObjectOutputStream(os);
           oos.writeObject(o);
           oos.flush();
           return true ;
       }catch (Exception e){
          log.error(e);
           return false;
       }finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           if (os != null){
               try {
                   oos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
    }
    public static Object read(String fileName){
        InputStream  is = null ;
        ObjectInputStream ois =null ;
        try {
            File file = new File(path,fileName);
            is = new FileInputStream(file);
            ois = new ObjectInputStream(is);
             Object o = ois.readObject();
            return o ;
        }catch (Exception e){
            log.error(e);
            return null;
        }finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
