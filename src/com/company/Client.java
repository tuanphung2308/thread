package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class Client {
    private String serverAddress = "127.0.0.1";
    private int port = 5000;
    private Socket socket;

    public Client(String serverAddress, int port) {
            this.serverAddress = serverAddress;
            this.port = port;
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
        client.start();
    }

    public void start() {
        System.out.println("Initializing Connection");
        try {
            this.socket = new Socket(this.serverAddress, this.port);

            // sends output to the socket
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
            //assume the message you want to send to the server is in variable ‘line’

            String userInput;

            BufferedReader stdIn =
                    new BufferedReader(
                            new InputStreamReader(System.in));
            while ((userInput = stdIn.readLine()) != null) {
                out.writeUTF(userInput);
                if (userInput.toLowerCase().equals("over")) {
                    stdIn.close();
                    out.close();
                    this.socket.close();
                    break;
                }
            }

        } catch (Exception e) {
        }
    }
}
