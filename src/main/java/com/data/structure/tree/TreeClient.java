package com.data.structure.tree;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class TreeClient {
    public static void main(String[] args) {
        NodeManager nm = new NodeManager();
        int[] a = {5,8,9,6,4,7,1,3,2};
        for(int i:a){
            nm.add(i);
        }
        nm.showMiddleOrder();
        nm.delete(6);
        System.out.println();
        nm.showMiddleOrder();
    }
}
