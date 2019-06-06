package com.log.controller;


import java.util.Properties;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class PropertiesInit {
    public static void initPro(){
        Properties fromhadoop = PropertiesRead.readPro("conf/file.properties");
        ClusterManagerParam.setFile(fromhadoop);
    }
}
