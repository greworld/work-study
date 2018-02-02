package com.gupaoedu.nazgd.fifo;

/**
 * Created by Zhanggd on 2017/8/14.
 */
public class ProducerThread implements Runnable {
    DistributeQueue<Message> queue;

    private static final int CLIENT_QTY = 100;//开启线程数

    public ProducerThread(DistributeQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=0;i<CLIENT_QTY;i++){
            try {
                Thread.sleep((int) (Math.random() * 5000));// 随机睡眠一下
                //消费
                Message sendMessage = new Message();
                sendMessage.setId(i);
                sendMessage.setName("message["+i+"]");
                queue.offer(sendMessage);

                System.out.println("发送一条消息成功：" + sendMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
