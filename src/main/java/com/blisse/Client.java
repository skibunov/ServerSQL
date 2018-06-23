package com.blisse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{

    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    @Override
    public synchronized void run() {
        try (Socket socket = new Socket("127.0.0.1",3345)){
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF("passauth");
            dos.writeUTF("Blisse");

            String password = dis.readUTF();
            System.out.println(password);


            dis.close();
            dos.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }
}
