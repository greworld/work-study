package com.greworld.zgd.zk.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/1/31.
 */
public class SessionDemo {

    private final static String CONNECTSTRING="192.168.190.101:2181,192.168.190.102:2181,192.168.190.105:2181";

    public static void main(String[] args) {
        ZkClient zkClient=new ZkClient(CONNECTSTRING,4000);

        System.out.println(zkClient+" - > success");
    }
}

