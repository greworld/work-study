package com.greworld.zgd.rpc.api;

import java.io.IOException;

/**
 * @author 风骚的GRE
 * @date 2018/1/30.
 */
public class User {
    private int age;

    public int getAge() throws IOException {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
