package com.gupaoedu.nazgd.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhanggd on 2017/8/12.
 */
public class MasterSelectorTest {
    private static final String CONNECTSTRING = "192.168.190.101:2181,192.168.190.102:2181" +
            ",192.168.190.104:2181,192.168.190.105:2181";
    //启动的服务个数
    private static final int CLIENT_QTY = 10;

    public static void main(String[] args) throws IOException {
        //保存zkClient服务列表
        List<ZkClient> clients = new ArrayList<ZkClient>();
        //保存所有服务的列表
        List<MasterSelector> selectors = new ArrayList<MasterSelector>();
        try {
            for (int i = 0; i < 10; i++) {
                ZkClient zkClient = new ZkClient(CONNECTSTRING, 5000, 5000, new SerializableSerializer());
                clients.add(zkClient);
                //机器信息
                WorkServer workServer = new WorkServer();
                workServer.setName("客户端[" + i + "]");
                workServer.setCid(i);

                MasterSelector selector = new MasterSelector(workServer);
                selector.setZkClient(zkClient);
                selectors.add(selector);

                //启动选举
                selector.start();
                Thread.sleep(1000);
            }
            System.out.println("敲回车键退出！\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            for(MasterSelector selector:selectors){
                selector.stop();
            }
        }
    }
}
