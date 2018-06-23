package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client{

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1",3345)){
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF("passauth");
            dos.writeUTF("Blisse");

            String password = dis.readUTF();
            System.out.println(password);

        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }


}
