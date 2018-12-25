package com.qbk.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationHome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: quboka
 * @Date: 2018/12/11 10:45
 * @Description: 命令执行工具
 */
@Log4j2
public class CommandUtil {

    /**
     * 执行String数组的命令
     */
    public static String runCommands(String... cmds) {
        String str = "";
        String errStr = "";
        String charset = "GBK";
        try {
            Process p = Runtime.getRuntime().exec(cmds);
            InputStream err = p.getErrorStream();
            InputStream in = p.getInputStream();
            str = processStdout(in, charset);
            errStr = processStdout(err, charset);
            int result = p.waitFor();
            System.out.println("执行命令结果为:"+result);
            if (!StringUtils.isEmpty(errStr)) {
                return str + errStr;
            }
        } catch (Exception e) {
            errStr = e.getMessage();
            return errStr;
        }
        return str;
    }


    /**
     * 解析返回信息
     */
    private static String processStdout(InputStream in, String charset) throws IOException {
        String s = "";
        String str = "";
        InputStreamReader insr = new InputStreamReader(in,charset);
        BufferedReader buffer = new BufferedReader(insr);
        try {
            while ((s = buffer.readLine()) != null) {
                if (StringUtils.isEmpty(str)) {
                    str = str + s;
                } else {
                    str = "\n" + str + s;
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            buffer.close();
            insr.close();
        }

        return str;
    }


    /**
     * 获取系统
     * @return
     */
    public static String systemCheck(){
        Properties prop = System.getProperties();
        //	操作系统的名称
        String os = prop.getProperty("os.name").toLowerCase();
        log.info("当前操作系统：【{}】",os);
        return os;
    }

    /**
     * 获取用户执行命令路径
     */
    public static String getUserPath(){
        //用户的当前工作目录
        String userDir = System.getProperty("user.dir");
        return userDir ;
    }

    /**
     * 获取程序主路径
     */
    public static String getHomePath(){
        return new ApplicationHome().toString();
    }

    /**
     * 执行String数组的命令
     */
    public static Map<String,Object> execute(String... command) {

        Map<String,Object> resultMap =new HashMap<String,Object>(2);

        /*
        不同点： ProcessBuilder.start() 和 Runtime.exec()传递的参数有所不同
        Runtime.exec()可接受一个单独的字符串，这个字符串是通过空格来分隔可执行命令程序和参数的；也可以接受字符串数组参数。
        而ProcessBuilder的构造函数是一个字符串列表或者数组。列表中第一个参数是可执行命令程序，其他的是命令行执行是需要的参数。
         */
        ProcessBuilder pBuilder = new ProcessBuilder(command);
        /**
         * ProcessBuilder的environment方法获得运行进程的环境变量,得到一个Map,可以修改环境变量
         */
        Map<String, String> map = pBuilder.environment();
        /*
        此属性为true，则通过子进程所产生的任何错误输出随后由该对象的start()方法启动将与标准输出合并，
        这样既可以用Process.getInputStream()方法来读取。此使得更容易与对应的输出相关的错误消息。初始值是false。
         */
        pBuilder.redirectErrorStream(true);
        StringBuilder builder = null;
        InputStreamReader isr = null;
        BufferedReader bw = null ;
        try {
            Process p = pBuilder.start();
            isr = new InputStreamReader(p.getInputStream(),"GBK");
            bw = new BufferedReader(isr);
            builder = new StringBuilder();
            String s;
            while ((s = bw.readLine()) !=null) {
                builder.append(s+"\n");

            }
             /*
              0：成功
              1：操作不允许
              2：没有这样的文件或目录
             */
            int code = p.waitFor();
            System.out.println("执行命令结果为:"+code);
            p.destroy();
            resultMap.put("code",code);
        } catch (Exception e) {
            System.out.println("执行命令异常:"+ e);
        }finally {
            if(bw !=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(isr !=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (builder !=null && builder.length() > 0) {
            resultMap.put("data",builder.substring(0,builder.length()-1));

        }

        return resultMap;
    }

    public static void main(String[] args){
        final String s = systemCheck();
        System.out.println("系统："+s);

        //netsh interface
        final Map<String, Object> explorer1 = execute("netsh" ,"interface","show","interface");
        System.out.println(explorer1);

        // netsh interface ipv4 show
        final Map<String, Object> explorer5 = execute("netsh" ,"interface","ipv4","show","addresses");
        System.out.println(explorer5);

        final String explorer4 = runCommands("ping" ,"10.1.24.243");
        System.out.println(explorer4);

        //修改ip   第一个参数是可执行命令程序，其他的是命令行执行是需要的参数。
        final Map<String, Object> execute = execute("netsh", "interface", "ip", "set", "address", "以太网", "static", "10.1.24.243", "255.255.255.0", "10.1.24.1");
        System.out.println(execute);

        //修改dns
        final String explorer3 =  runCommands(" netsh interface ip set dns \"以太网\" static 114.114.114.114 primary");
        System.out.println(explorer3);


        //加入开机启动项
        final String userPath = getHomePath();
        final Map<String, Object> explorer6 = execute("reg","add","\"HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\run\"",
                "/v","中间件","/d",userPath+"\\中间件.exe","/f");
        System.out.println(explorer6);

    }



}
