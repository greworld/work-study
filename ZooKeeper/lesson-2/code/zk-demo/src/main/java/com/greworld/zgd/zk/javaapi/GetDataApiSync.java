package com.greworld.zgd.zk.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/1.
 */
public class GetDataApiSync implements Watcher {
    private final static String CONNECTSTRING="192.168.190.101:2181,192.168.190.102:2181,192.168.190.105:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();
    private static final String PATH = "/zk-book";

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper = new ZooKeeper(CONNECTSTRING,5000,new GetDataApiSync());
        countDownLatch.await();
        zooKeeper.create(PATH,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getData(PATH,true,new IDataCallBack(),null);
        zooKeeper.setData(PATH,"123".getBytes(),-1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected==watchedEvent.getState()){
            if (Event.EventType.None==watchedEvent.getType()&&null==watchedEvent.getPath()){
                countDownLatch.countDown();
            }else if (watchedEvent.getType()== Event.EventType.NodeDataChanged){
                try {
                    zooKeeper.getData(PATH,true,new IDataCallBack(),null);
                }catch (Exception e){

                }
            }
        }
    }


}

class IDataCallBack implements AsyncCallback.DataCallback{
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println(rc+" ,"+path+","+new String(data));
        System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
    }
}