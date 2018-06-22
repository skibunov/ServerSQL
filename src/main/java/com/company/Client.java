package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    static {
        try {
            socket = new Socket("127.0.0.1",3345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        read();
        write();
    }

    public static void read(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!socket.isClosed()){
                    try {
                        System.out.println(in.readUTF());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {

                    }
                }
            }
        });
        thread.start();
    }

    public static void write(){
        Thread thread = new Thread(new Runnable() {
            Scanner scanner = new Scanner(System.in);
            @Override
            public void run() {
                while (!socket.isClosed()){
                    String text = scanner.nextLine();
                    try {
                        out.writeUTF(text);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
