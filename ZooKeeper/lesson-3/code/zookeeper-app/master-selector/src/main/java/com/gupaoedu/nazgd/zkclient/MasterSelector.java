package com.gupaoedu.nazgd.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Zhanggd on 2017/8/11.
 */
public class MasterSelector {

    private boolean running = false;//记录服务器运行状态

    private ZkClient zkClient;//客户端

    private static final String MASTER_PATH = "/master";//master节点对应在zookeeper中的节点路径

    private IZkDataListener dataListener;//监听master节点的删除事件

    private WorkServer masterInfo;//master机器服务信息

    private WorkServer slaveInfo;//集群中当前服务器节点的基本信息

    //利用线程去释放
    private ScheduledExecutorService delayExector = Executors.newScheduledThreadPool(1);

    //暂停5秒再释放
    private int delayTime = 5;

    //创建master选举对象的时候，需要将服务机器信息注入进来
    public MasterSelector(WorkServer workServer) {
        System.out.println("[" + workServer + "]去争抢mater权限...");
        this.slaveInfo = workServer;

        //监听master的删除事件
        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                //节点变化
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //监听节点删除事件,如果节点被删除，则发起mater选举操作
                //若之前master为本机,则立即抢主,否则延迟5秒抢主(防止小故障引起的抢主可能导致的网络数据风暴)
                if (masterInfo!=null&&masterInfo.getName().equals(slaveInfo.getName())){
                    takeMater();
                }else {
                    delayExector.schedule(()->{
                        takeMater();
                    },delayTime,TimeUnit.SECONDS);
                }
            }
        };
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    /**
     * 启动服务
     */
    public void start() {

        if (!running){
            running = true;
            //在zk上注册监听事件
            zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
            //开始选举
            takeMater();
        }
    }

    /**
     * 停止服务
     */
    public void stop() {
        if (running){
            running = false;
            //取消监听
            delayExector.shutdown();
            zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
            //释放锁
            releaseMater();
        }
    }

    /**
     * 争抢master
     */
    public void takeMater() {
        if (!running) {
            System.out.println("ZooKeeper服务器没有启动...");
            return;
        }
        try {
            zkClient.createEphemeral(MASTER_PATH, slaveInfo);
            //创建成果，标记自己为master
            masterInfo = slaveInfo;
            System.out.println("被选举的master服务器为：[" + masterInfo.getName() + "]");
            //故障
            delayExector.schedule(() -> {
                //释放锁
                if (checkMaster()){
                    releaseMater();
                }

            }, delayTime, TimeUnit.SECONDS);
        } catch (ZkNodeExistsException e) {
            //表示master已经存在
            WorkServer workServer = zkClient.readData(MASTER_PATH, true);
            if (workServer != null) {
                //System.out.println("["+workServer.getName()+"]启动选举操作");
                checkMaster();
            } else {
                releaseMater();
            }
        }
    }

    /**
     * 释放master
     */
    public void releaseMater() {
        //释放锁(故障模拟过程)
        //判断当前是不是master，只有master才需要释放
        if (checkMaster()) {
            //删除节点的过程就是释放锁
            System.out.println(masterInfo.getName()+"释放锁");
            zkClient.delete(MASTER_PATH);
        }
    }

    /**
     * 检查是否为mater
     */
    public boolean checkMaster() {
        try {
            WorkServer workServer = zkClient.readData(MASTER_PATH);
            masterInfo = workServer;
            if (masterInfo.getName().equals(slaveInfo.getName())) {
                return true;
            }
            return false;
        } catch (ZkNoNodeException e) {
            return false;
        } catch (ZkInterruptedException e) {
            return checkMaster();
        } catch (ZkException e) {
            return false;
        }

    }

}
