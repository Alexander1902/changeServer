package com.log.controller;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class PropertiesRead {
    private static Logger log= Logger.getLogger(PropertiesRead.class);
    /**
     *
     * @param path 输入读取的resource路径，如：conf/tohadoop.properties,本方法自动判断时jar模式还是编程模式
     * @return
     */
    public static Properties readPro(String path){
        Properties pro = new Properties();
        //file:/E:/code/java/clusterManager/target/rail/lib/clusterManager-1.0.jar!/ctzcdn/log/util/
        String resource = PropertiesRead.class.getResource("").getPath();
        if(!resource.contains("!")){
            log.info("编程模式启动");
            ClassLoader classLoader = PropertiesRead.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream(path);
            try {
                pro.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            log.info("正常模式启动");
            // file:/E:/code/java/clusterManager/target/rail/lib/clusterManager-1.0.jar
            String s = resource.split("!")[0];
            int i = s.indexOf("file:");
            String substring = s.substring(i + 5);
            File file = new File(substring);
            String parent = file.getParentFile().getParent();
            log.info("工程的根路径为："+parent);
            File sysPath = new File(parent + File.separatorChar + path);
            try {
                Reader reader = new FileReader(sysPath);
                pro.load(reader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return pro;

    }


}
