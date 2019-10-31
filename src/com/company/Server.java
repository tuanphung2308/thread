package com.company;

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private int port = 5000;
    private volatile int clientCount = 0;
    private static Server instance;

    public Server(int port) {
        this.port = port;
    }

    public static Server getInstance() {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null)
                    instance = new Server(5000);
            }
        }
        return instance;
    }

    public void decreaseCount () {
        clientCount--;
        System.out.println("Connected to: " + clientCount + " clients");

    }

    public void incrementCount() {
        clientCount++;
        System.out.println("Connected to: " + clientCount + " clients");
    }

    public void start(){
        try {
            System.out.println("Server is now running");
            ServerSocket server;
            server = new ServerSocket(this.port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("Connection Accepted");
                ServerSocketThread serverSocket = new ServerSocketThread(socket, String.valueOf(socket.hashCode()));
                new Thread(serverSocket).start();
            }
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server s = new Server(5000);
        s.start();
    }
}
