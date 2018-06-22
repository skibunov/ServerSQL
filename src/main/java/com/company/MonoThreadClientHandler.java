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

           
                if (type.equals("passauth")){

                    String username = in.readUTF();

                    if (username == null){

                        out.writeUTF("Неизвестное имя");
                        out.flush();
                    }else{
                        out.writeUTF(auth.passAuth(username));
                    }

                }

                if (type.equals("keyAuth")){


                    


                }

                if(type.equals("server")){
                    System.out.println("server accept");

                    System.out.println(in.available());

                    String name = in.readUTF();

                    System.out.println("name - " + name);
                    String ip = auth.getServer(name, "ip");

                    int port = Integer.parseInt(auth.getServer(name, "port"));
                    out.writeUTF(ip);
                    out.flush();
                    System.out.println("Отправлен ip " + ip);

                    //out.writeInt(port);
                    //System.out.println("Отправлен port " + port);
                    //out.flush();
                }
            }

            System.out.println("Client disconnected");

            in.close();
            out.close();

            clientDialog.close();

        } catch (IOException e) {

        }
    }
}