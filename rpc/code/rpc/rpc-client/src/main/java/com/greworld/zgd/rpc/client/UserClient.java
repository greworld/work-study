package com.greworld.zgd.rpc.client;


import com.greworld.zgd.rpc.api.User;

import java.io.IOException;

/**
 * @author 风骚的GRE
 * @date 2018/1/30.
 */
public class UserClient {
    public static void main(String[] args) throws IOException {
        User user=new User_Stub();

        int age=user.getAge();

        System.out.println(age);
    }

}
