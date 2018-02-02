package com.gupaoedu.nazgd.zkclient.threadpool;

/**
 * Created by Zhanggd on 2017/8/14.
 */
public class MasterThread implements Runnable {

    MasterSelectorThread masterSelector;

    public MasterThread(MasterSelectorThread masterSelector) {
        this.masterSelector = masterSelector;
    }

    @Override
    public void run() {
        try {
            masterSelector.start();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
