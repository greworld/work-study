package com.greworld.zgd.rpc.server;

import com.greworld.zgd.rpc.api.User;

/**
 * @author 风骚的GRE
 * @date 2018/1/30.
 */
public class UserServer extends User{
    public static void main(String[] args) {
        UserServer userServer=new UserServer();
        userServer.setAge(18);

        User_Skeleton skel=new User_Skeleton(userServer);

        skel.start();
    }

}
