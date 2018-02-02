package com.greworld.zgd.zk.curator;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/1.
 */
public class TreeCacheDemo {
    private final static String CONNECTSTRING="192.168.190.101:2181,192.168.190.102:2181,192.168.190.105:2181";
    private static final String MASTER_PATH="/curator_master_path1";
    private static final int CLIENT_QTY=10; //客户端数量

    public static void main(String[] args) throws IOException {
        System.out.println("创建"+CLIENT_QTY+"个客户端，");
        List<CuratorFramework> clients = Lists.newArrayList();
        List<ExampleClient>     examples = Lists.newArrayList();
        try {
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework client = CuratorFrameworkFactory.newClient(CONNECTSTRING,
                        new ExponentialBackoffRetry(1000, 3));
                clients.add(client);
                ExampleClient exampleClient = new ExampleClient(client, MASTER_PATH, "Client:" + i);
                examples.add(exampleClient);
                client.start();
                exampleClient.start();
            }
            System.in.read();
        }finally {
            for ( ExampleClient exampleClient : examples ){
                CloseableUtils.closeQuietly(exampleClient);
            }
            for ( CuratorFramework client : clients ){
                CloseableUtils.closeQuietly(client);
            }
        }
    }

}
