package com.gupaoedu.nazgd.zkclient;

import java.io.Serializable;

/**
 * Created by Zhanggd on 2017/8/11.
 */
public class WorkServer implements Serializable{
    private static final long serialVersionUID = -1776114173857775665L;

    private String name;//进程名称

    private long cid;//进程ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "WorkServer{" +
                "name='" + name + '\'' +
                ", cid=" + cid +
                '}';
    }
}
