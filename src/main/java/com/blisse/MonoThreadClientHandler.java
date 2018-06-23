package com.blisse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;


    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {
        System.out.print("Connection accepted. Ip - "+clientDialog.getInetAddress()+":"+clientDialog.getLocalPort()+"\n");
        try {

            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());


            while (!clientDialog.isClosed()) {
                network auth = new network();

                String type = in.readUTF();


                if (type.equals("passauth")) {

                    String username = in.readUTF();

                    if (username == null) {

                        out.writeUTF("Неизвестное имя");
                        out.flush();
                    } else {
                        out.writeUTF(auth.passAuth(username));
                        out.flush();
                    }

                }

            }
        }catch (IOException e){
            System.out.print("Client Disconnect. Ip - "+clientDialog.getInetAddress()+":"+clientDialog.getLocalPort()+"\n");
        }
    }
}