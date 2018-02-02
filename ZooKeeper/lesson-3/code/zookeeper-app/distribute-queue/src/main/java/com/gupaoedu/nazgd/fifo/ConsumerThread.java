package com.gupaoedu.nazgd.fifo;

/**
 * Created by Zhanggd on 2017/8/14.
 */
public class ConsumerThread implements Runnable {

    DistributeQueue<Message> queue;

    private static final int CLIENT_QTY = 100;//开启线程数

    public ConsumerThread(DistributeQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=0;i<CLIENT_QTY;i++){
            try {
                Thread.sleep((int) (Math.random() * 5000));// 随机睡眠一下
                //消费
                Message sendMessage = queue.poll();
                System.out.println("消费一条消息成功：" + sendMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
