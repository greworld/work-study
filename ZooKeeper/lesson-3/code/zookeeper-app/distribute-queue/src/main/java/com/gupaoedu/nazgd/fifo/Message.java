package com.gupaoedu.nazgd.fifo;

import java.io.Serializable;

/**
 * 队列的存储数据
 * Created by Zhanggd on 2017/8/12.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
