package com.gupaoedu.nazgd.javaapilock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zhanggd on 2017/8/10.
 */
public class DistributeLock {

    private ZooKeeper zooKeeper;

    private static final String ROOT_LOCKS = "/LOCKS";//根节点

    private static byte[] data = {1, 2, 3};

    private static int sessionTimeout;

    private String lockID; //记录ID

    private CountDownLatch countDownLatch=new CountDownLatch(1);

    private Stat stat;

    //初始化实例的时候就创建ZooKeeper对象和获取会话超时时间
    public DistributeLock() throws IOException, InterruptedException {
        this.zooKeeper = ZookeeperClient.getInstance();
        this.sessionTimeout = ZookeeperClient.getSessionTimeout();
    }

    /**
     * 根节点是否已经存在
     *
     * @return
     */
    public Stat existLocks() throws KeeperException, InterruptedException {
        this.stat = zooKeeper.exists(ROOT_LOCKS, true);
        return stat;
    }

    /**
     * 获取锁
     *
     * @return
     */
    public boolean lock() {
        try {
            //判断节点是否存在
            /*if (this.stat == null) {
                zooKeeper.create(ROOT_LOCKS, data, ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
            }*/
            //创建临时有序节点
            lockID=zooKeeper.create(ROOT_LOCKS+"/",data, ZooDefs.Ids.
                    OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "->成功创建了lock节点，[" + lockID + "]开始去竞争");

            //获取LOCKS节点下的所有子节点
            List<String> childrenNode = zooKeeper.getChildren(ROOT_LOCKS,true);

            //从小到大排序
            SortedSet<String> sortedSetChildren = new TreeSet<String>();
            for(String childNode:childrenNode){
                sortedSetChildren.add(ROOT_LOCKS+"/"+childNode);
            }

            //获取排序的第一个节点（最小的节点）
            String firs = sortedSetChildren.first();

            if (lockID.equals(firs)){//表示当前就是最小的节点
                System.out.println(Thread.currentThread().getName()+"->成功获得锁，lock节点为:["+lockID+"]");
                return true;
            }

            //如果当前节点不是第一个节点
            SortedSet<String> lessThanLockId = sortedSetChildren.headSet(lockID);
            if (!lessThanLockId.isEmpty()){
                //拿到比当前LOCKID这个几点更小的上一个节点
                String prevLock = lessThanLockId.last();
                zooKeeper.exists(prevLock,new LockWatcher(countDownLatch));
                countDownLatch.await(sessionTimeout, TimeUnit.SECONDS);
                //上面这段代码意味着如果会话超时或者节点被删除（释放）了
                System.out.println(Thread.currentThread().getName()+" 成功获取锁：["+lockID+"]");
            }
            return true;

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @return
     */
    public boolean unLock() {
        System.out.println(Thread.currentThread().getName()+"->开始释放锁:["+lockID+"]");
        try {
            zooKeeper.delete(lockID,-1);
            System.out.println("节点["+lockID+"]成功被删除");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        final CountDownLatch latch=new CountDownLatch(10);
        Random random=new Random();
        for (int i=0;i<10;i++){
            new Thread(()->{
                DistributeLock distributeLock = null;
                try {
                    distributeLock = new DistributeLock();
                    latch.countDown();
                    latch.await();
                    distributeLock.lock();
                    Thread.sleep(random.nextInt(500));
                }catch (IOException e){
                    e.getMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (distributeLock!=null){
                        distributeLock.unLock();
                    }
                }
            }).start();
        }
    }

}
