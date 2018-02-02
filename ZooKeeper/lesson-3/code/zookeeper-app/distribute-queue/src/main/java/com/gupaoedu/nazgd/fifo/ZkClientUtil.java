package com.gupaoedu.nazgd.fifo;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * Created by Zhanggd on 2017/8/14.
 */
public class ZkClientUtil {

    private static final String CONNECTSTRING = "192.168.190.101:2181,192.168.190.102:2181" +
            ",192.168.190.105:2181";

    private static ZkClient zkClient;

    /**
     * 获取ZkClient对象实例
     * @return
     */
    public static ZkClient getInstance(){
        zkClient = new ZkClient(CONNECTSTRING, 5000, 5000, new SerializableSerializer());
        return zkClient;
    }

    /**
     * 关闭客户端连接
     *
     */
    public static void close(){
        zkClient.close();
    }

}
