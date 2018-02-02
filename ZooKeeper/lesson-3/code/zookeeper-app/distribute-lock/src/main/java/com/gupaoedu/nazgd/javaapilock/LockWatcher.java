package com.gupaoedu.nazgd.javaapilock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Zhanggd on 2017/8/10.
 */
public class LockWatcher implements Watcher{

    private CountDownLatch countDownLatch;

    public LockWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType()== Event.EventType.NodeDeleted){
            System.out.println("节点："+watchedEvent.getPath()+" 被删除");
            countDownLatch.countDown();
        }
    }
}
