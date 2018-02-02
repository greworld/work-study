package com.greworld.zgd.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/1.
 */
public class CuratorOperatorDemo {
    private static Stat stat = new Stat();
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework=CuratorClientUtils.getInstance();
        System.out.println("连接成功.........");

        //fluent风格
        // 创建节点
        String result = curatorFramework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/curator/curator1/curator13","123".getBytes());
        System.out.println(result);

        // 删除节点
        //默认情况下，version为-1
       // curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator");

        //查询
        byte[] bytes=curatorFramework.getData().storingStatIn(stat).forPath("/curator");
        System.out.println(new String(bytes)+"-->stat:"+stat);

        // 更新
        Stat stat=curatorFramework.setData().forPath("/curator","123".getBytes());
        System.out.println(stat);

        /**
         * 异步操作
         */
        /*ExecutorService service = Executors.newFixedThreadPool(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).
                    inBackground(new BackgroundCallback() {
                        @Override
                        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                            System.out.println(Thread.currentThread().getName()+"->resultCode:"+curatorEvent.getResultCode()+"->"
                                    +curatorEvent.getType());
                            countDownLatch.countDown();
                        }
                    },service).forPath("/gre","123".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        service.shutdown();*/


        /**
         * 事务操作（curator独有的）
         */
        /*
        try {
            Collection<CuratorTransactionResult> resultCollections=curatorFramework.inTransaction().create().forPath("/trans","111".getBytes()).and().
                    setData().forPath("/curator","111".getBytes()).and().commit();
            for (CuratorTransactionResult result:resultCollections){
                System.out.println(result.getForPath()+"->"+result.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}
