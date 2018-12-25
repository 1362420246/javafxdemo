package com.qbk.util;

import lombok.extern.log4j.Log4j2;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/**
 * 操作系统工具
 */
@Log4j2
public class OsNetUtil {


    /**
     * 获取网卡名
     */
    public static List<String> getWindowsAddresses(){
        List<String> result = new ArrayList<>();
        try{
            if(CommandUtil.systemCheck().startsWith("windows")){
                Map<String, Object> execute = CommandUtil.execute("netsh", "interface", "ipv4", "show", "addresses");
                if (execute == null || (Integer)execute.get("code") != 0){
                    return result;
                }
                String data = (String) execute.get("data");
                System.out.println(data);
                String[] arr = data.split("接口");
                List<String> list = new ArrayList<>(Arrays.asList(arr));
                Iterator<String> iterator = list.iterator();
                while(iterator.hasNext()){
                    String str = iterator.next();
                    if(!str.contains("IP 地址")){
                        iterator.remove();
                    }
                }
                for (String str :list) {
                    String substring = str.substring(str.indexOf("\"")+1, str.indexOf("\"", 2));
                    result.add(substring);
                }
            }else{
                // 获得本机的所有网络接口
                Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
                while (nifs.hasMoreElements()) {
                    NetworkInterface nif = nifs.nextElement();
                    // 获得与该网络接口绑定的 IP 地址，一般只有一个
                    Enumeration<InetAddress> addresses = nif.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        // 只关心 IPv4 地址
                        if (addr instanceof Inet4Address) {
                            System.out.println("网卡接口名称：" + nif.getName());
                            System.out.println("网卡接口地址：" + addr.getHostAddress());
                            result.add(nif.getName());
                        }
                    }
                }
            }
        }catch (Exception e){
            log.error(e);
        }
        return result ;
    }

    /**
     *  修改ip
     * @param name 接口名称
     * @param ip  ip
     * @param subnetMask 子网掩码
     * @param gateway 网关
     */
    public static boolean updateWindowsAddresses(String name ,String  ip ,String subnetMask,String gateway ){
        if(CommandUtil.systemCheck().startsWith("windows")){
            //修改ip   第一个参数是可执行命令程序，其他的是命令行执行是需要的参数。
            Map<String, Object> execute = CommandUtil.execute("netsh", "interface", "ip", "set", "address", name, "static",ip, subnetMask, gateway);
            if(execute !=null && (Integer)execute.get("code") == 0 ){
                return true;
            }
        }else {
            String userPath = CommandUtil.getHomePath();
            Map<String, Object> execute = CommandUtil.execute("sh",userPath +"/config/ip.sh",ip,subnetMask,gateway,name);
            System.out.println(execute);
            if(execute !=null && (Integer)execute.get("code") == 0 ){
                return true;
            }
        }

        return false ;
    }

    /**
     * 加入开机启动项
     */
    public static boolean regAdd(){
        if(CommandUtil.systemCheck().startsWith("windows")){
            String userPath = CommandUtil.getUserPath();
            Map<String, Object> execute = CommandUtil.execute("reg","add","\"HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\run\"",
                    "/v","中间件","/d",userPath+"\\中间件.exe","/f");
            if(execute !=null && (Integer)execute.get("code") == 0 ){
                return true;
            }
        }else {
            String userPath = CommandUtil.getHomePath();
            Map<String, Object> execute = CommandUtil.execute("bash",userPath +"/config/copy.sh","cd "+userPath);
            if(execute !=null && (Integer)execute.get("code") == 0 ){
                return true;
            }
        }
        return false ;
    }

    /**
     * 打开页面
     */
    public static void openWebpage(String url){

        String result = null;

        if(CommandUtil.systemCheck().startsWith("windows")){
            result = CommandUtil.runCommands("rundll32","url.dll,FileProtocolHandler", url);
        }else {

            try{
                // Unix or Linux的打开方式
                String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++){
                    // 执行代码，在brower有值后跳出，
                    // 这里是如果进程创建成功了，==0是表示正常结束。
                    if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser == null) {
                    throw new Exception("Could not find web browser");
                } else {
                    // 这个值在上面已经成功的得到了一个进程。
                    result = CommandUtil.runCommands(new String[] { browser, url });
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        System.out.println(result);
    }

}
