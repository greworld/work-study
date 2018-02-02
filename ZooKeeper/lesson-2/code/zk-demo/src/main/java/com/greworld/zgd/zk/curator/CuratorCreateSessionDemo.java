package com.greworld.zgd.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/1.
 */
public class CuratorCreateSessionDemo {
    private final static String CONNECTSTRING="192.168.190.101:2181,192.168.190.102:2181,192.168.190.105:2181";

    public static void main(String[] args) {
        //创建会话的两种方式 normal
       /* CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECTSTRING,
                5000,
                1000,
                new ExponentialBackoffRetry(1000,3));
        // 通过 curatorFramework.start()启动
        curatorFramework.start();*/

       CuratorFramework curatorFramework1  = CuratorFrameworkFactory.builder()
                .connectString(CONNECTSTRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3,5000))
                //.namespace("curator")
                .build();
        curatorFramework1.start();
        System.out.println("success");
    }
}
