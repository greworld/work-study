package com.gupaoedu.nazgd.javaapilock;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
* DistributeLock Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 10, 2017</pre> 
* @version 1.0 
*/ 
public class DistributeLockTest {

    @Test
    public void TestLock(){
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
