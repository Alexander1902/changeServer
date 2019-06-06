package com.log.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhangshancheng
 **/

@Controller
@RequestMapping("/upload")
public class FileManagerContrller {

    private static Logger log= Logger.getLogger(FileManagerContrller.class);

    @RequestMapping(value = "/isOk",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,String> getFile(HttpServletRequest request){
        Map<String,String> res = new HashMap<String,String>();
        System.out.println("测试通不通");
        res.put("status","ok");
        return res;
    }

    @RequestMapping(value = "/file/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,String> receieFile(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response){
        System.out.println(id);
        String fileSave = ClusterManagerParam.getFile().getProperty("fileSave");
        File file = new File(fileSave);
        File[] files = file.listFiles();
        Map<String,String> map = new HashMap<String,String>();
        int i = Integer.parseInt(id);
        if(i>=files.length){
            map.put("error","数太大了");
        }else{
            File file1 = files[i];
            response.setContentType("multipart/form-data");
            response.reset();
            response.setHeader("Content-Disposition", "attachment;fileName=" + file1.getName());
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.addHeader("Content-Length", "" + file1.length());
            response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
            response.setCharacterEncoding("utf-8");

            OutputStream out = null;
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file1);
                out = response.getOutputStream();
                byte[] buffer = new byte[20480];
                int count = 0;
                while ((count = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            map.put("info","ok");
        }

        return map;
    }
    @RequestMapping(value = "/name/{id}.*",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Map<String,String> receieFilName(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response){
        System.out.println(id);
        String fileSave = ClusterManagerParam.getFile().getProperty("fileSave");
        File file = new File(fileSave);
        File[] files = file.listFiles();
        Map<String,String> map = new HashMap<String,String>();
        File send = null;
        for(File f:files){
            if(id.equals(f.getName())){
                send = f;
            }
        }
        if(send!=null){
            map.put("error","数太大了");
        }else{
            response.setContentType("multipart/form-data");
            response.reset();
            response.setHeader("Content-Disposition", "attachment;fileName=" + send.getName());
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.addHeader("Content-Length", "" + send.length());
            response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
            response.setCharacterEncoding("utf-8");

            OutputStream out = null;
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(send);
                out = response.getOutputStream();
                byte[] buffer = new byte[20480];
                int count = 0;
                while ((count = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //map.put("info","ok");
        }

        return map;
    }



}
