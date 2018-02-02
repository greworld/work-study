package com.greworld.zgd.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/1.
 */
public class CuratorClientUtils {
    private final static String CONNECTSTRING="192.168.190.101:2181,192.168.190.102:2181,192.168.190.105:2181";
    private static CuratorFramework curatorFramework;

    public static CuratorFramework getInstance(){
        curatorFramework  = CuratorFrameworkFactory.builder()
                .connectString(CONNECTSTRING)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3,5000))
                //.namespace("curator")
                .build();
        curatorFramework.start();
        return curatorFramework;
    }

}
