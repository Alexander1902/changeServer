package com.data.structure.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: 去掉缓冲区
 * @author: zhangshancheng
 **/
public class ChannelTest2 {
    public  static void main(String[] args) throws IOException {
        RandomAccessFile afile = new RandomAccessFile("/E:/object/IPLib.acl","rw");
        FileChannel inChannel = afile.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(102400);
        File infile = new File("/E:/object/IPLib1.acl");
        FileOutputStream out = new FileOutputStream(infile);
        FileChannel outChannel = out.getChannel();
        int pos = 0;
        int count = 0;
        long start = System.currentTimeMillis();
        long size = inChannel.size();
        outChannel.transferFrom(inChannel,0,size);
        System.out.println(count);
        System.out.println(System.currentTimeMillis()-start+"ms");


    }
}
