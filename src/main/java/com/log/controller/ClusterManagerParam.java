package com.log.controller;

import java.util.Properties;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class ClusterManagerParam {
    private static ClusterManagerParam param = new ClusterManagerParam();
    private String systemPath = "";
    private Properties file = null;
    private ClusterManagerParam(){}

    public static void setSystemPath(String systemPath){
        param.systemPath = systemPath;
    }

    public static void setFile(Properties file){
        param.file = file;
    }


    public static String getSystemPath(){
        return param.systemPath;
    }

    public static Properties getFile(){
        return param.file;
    }

}
