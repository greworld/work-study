package com.gupaoedu.nazgd.zkclient.threadpool;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * Created by Zhanggd on 2017/8/12.
 */
public class MaterSelectorThreadTest {
    private static final String CONNECTSTRING = "192.168.190.101:2181,192.168.190.102:2181" +
            ",192.168.190.104:2181,192.168.190.105:2181";

    private static final int CLIENT_QTY = 10;//开启线程数

    public static void main(String[] args) {
        //保存所有服务的列表
        ZkClient zkClient = new ZkClient(CONNECTSTRING, 5000, 5000, new SerializableSerializer());
        //利用线程去选举
        for (int i = 0;i<CLIENT_QTY;i++){
            WorkServer workServer = new WorkServer();
            workServer.setName("客户端[" + i + "]");
            workServer.setCid(i);
            MasterSelectorThread masterSelectorThread = new MasterSelectorThread(workServer,zkClient);
            new Thread(new MasterThread(masterSelectorThread));
        }

    }
}
