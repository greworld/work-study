package com.greworld.zgd.rpc.client;


import com.greworld.zgd.rpc.api.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 风骚的GRE
 * @date 2018/1/30.
 */
public class User_Stub extends User {
    private Socket socket;
    public User_Stub() throws IOException {
        socket=new Socket("localhost",8888);
    }
    public int getAge() throws IOException {
        ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject("age");
        outputStream.flush();
        ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
        int age=objectInputStream.readInt();
        return age;
    }

}
