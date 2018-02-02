package com.gupaoedu.nazgd.javaapilock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Zhanggd on 2017/8/10.
 */
public class ZookeeperClient {
    private static final String CONNECTSTRING = "192.168.190.101:2181,192.168.190.102:2181" +
            ",192.168.190.104:2181,192.168.190.105:2181";

    private static int sessionTimeout=5000;

    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static ZooKeeper getInstance() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState()== Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();

        return zooKeeper;
    }

    public static int getSessionTimeout(){
        return sessionTimeout;
    }
}
