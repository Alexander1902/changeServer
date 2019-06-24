package com.data.structure.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class ChannelTest {
    public  static void main(String[] args) throws FileNotFoundException {
        RandomAccessFile afile = new RandomAccessFile("/E:/object/IPLib.acl","rw");
        FileChannel inChannel = afile.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024000);
        File infile = new File("/E:/object/IPLib1.acl");
        FileOutputStream out = new FileOutputStream(infile);
        FileChannel outChannel = out.getChannel();
        int pos = 0;
        int count = 0;
        long start = System.currentTimeMillis();
        while(true){
            count++;
            try {
                if ((inChannel.read(allocate))!=-1) {
                    allocate.flip();
                    outChannel.write(allocate);
                    allocate.clear();
                }else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println(count);
        System.out.println(System.currentTimeMillis()-start+"ms");


    }
}
