package com.greworld.zgd.zk.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 风骚的GRE
 * @Description 使用原生的Java-api操作ZK
 * @date 2018/1/31.
 */
public class ApiOperatorDemo implements Watcher{
    private final static String CONNECTSTRING="192.168.190.101:2181,192.168.190.102:2181,192.168.190.105:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zooKeeper =new ZooKeeper(CONNECTSTRING, 5000, new ApiOperatorDemo());
        countDownLatch.await();
        //createNode();
        //update("gre1235");
        //update("gre123456");
        //delete();

        //创建节点和子节点
        String path="/node11";

        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        TimeUnit.SECONDS.sleep(1);

        Stat stat=zooKeeper.exists(path+"/node1",true);
        if(stat==null){//表示节点不存在
            zooKeeper.create(path+"/node1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            TimeUnit.SECONDS.sleep(1);
        }
        //修改子路径
        zooKeeper.setData(path+"/node1","mic123".getBytes(),-1);
        TimeUnit.SECONDS.sleep(1);


        //获取指定节点下的子节点
       List<String> childrens=zooKeeper.getChildren("/node",true);
        System.out.println(childrens);

    }

    /**
     * 创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void createNode() throws KeeperException, InterruptedException {
        String result= zooKeeper.create("/node1","123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建成功："+result);
    }

    /**
     * 修改节点数据
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void update(String value) throws KeeperException, InterruptedException {
        zooKeeper.setData("/node1",value.getBytes(),-1);
        Thread.sleep(2000);
    }

    public static void delete() throws InterruptedException, KeeperException {
        zooKeeper.delete("/node10000000013",-1);
        Thread.sleep(2000);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        //如果当前的连接状态是连接成功的，那么通过计数器去控制
        if (watchedEvent.getState()==Event.KeeperState.SyncConnected){
            if (Event.EventType.None==watchedEvent.getType()&&null==watchedEvent.getPath()){
                countDownLatch.countDown();
                System.out.println(watchedEvent.getState()+"-->"+watchedEvent.getType());
            }else if(watchedEvent.getType()== Event.EventType.NodeDataChanged){
                try {
                    System.out.println("数据变更触发路径："+watchedEvent.getPath()+"->改变后的值："+
                            zooKeeper.getData(watchedEvent.getPath(),true,stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(watchedEvent.getType()== Event.EventType.NodeChildrenChanged){//子节点的数据变化会触发
                try {
                    System.out.println("子节点数据变更路径："+watchedEvent.getPath()+"->节点的值："+
                            zooKeeper.getData(watchedEvent.getPath(),true,stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(watchedEvent.getType()== Event.EventType.NodeCreated){//创建子节点的时候会触发
                try {
                    System.out.println("节点创建路径："+watchedEvent.getPath()+"->节点的值："+
                            zooKeeper.getData(watchedEvent.getPath(),true,stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if(watchedEvent.getType()== Event.EventType.NodeDeleted){//子节点删除会触发
                System.out.println("节点删除路径："+watchedEvent.getPath());
            }
            System.out.println(watchedEvent.getType());
        }
    }

}
