package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;


    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {

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
            e.printStackTrace();
        }
    }
}