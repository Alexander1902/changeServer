package com.log.controller;

import org.eclipse.jetty.server.Server;

/**
 * @description: ctzcdn.log.controller.ReceiveClient
 * @author: zhangshancheng
 **/
public class ReceiveClient {
    public static void main(String[] args){
        PropertiesInit.initPro();
        try {
            JettySeverBuilder jettySeverBuilder = new JettySeverBuilder();
            Server build = jettySeverBuilder.build();
            build.start();
            build.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

