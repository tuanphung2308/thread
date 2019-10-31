package com.company;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.Socket;

public class ServerSocketThread implements Runnable {
    Socket socket;
    String name;

    public ServerSocketThread(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            Server.getInstance().incrementCount();
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            String line;
            while ((line = in.readUTF()) != null) {
                System.out.println(name + ": " + line);
                if (line.toLowerCase().equals("over")) {
                    socket.close();
                    System.out.println("Closing connection");
                }
            }
        } catch (Exception e) {

        } finally {
            Server s = Server.getInstance();
            s.decreaseCount();
        }
    }
}
