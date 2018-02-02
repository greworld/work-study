package com.gupaoedu.nazgd.fifo;

import org.I0Itec.zkclient.ZkClient;

/**
 * Created by Zhanggd on 2017/8/12.
 */
public class DistributeQueueTest {

    public static void main(String[] args) {
        ZkClient zkClient = ZkClientUtil.getInstance();

        DistributeQueue<Message> queue = new DistributeQueue<Message>(zkClient,"/queue_fifo");
        //生产对象
        new Thread(new ProducerThread(queue)).start();
        //消费对象
        new Thread(new ConsumerThread(queue)).start();
    }

}
