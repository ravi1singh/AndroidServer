package com.example.firstapp;
import android.widget.TextView;

import java.io.*;
import java.net.*;

public class Server {
         Socket socket = null;
         ServerSocket server = null;
         BufferedReader in;
        // constructor with port
	public Server( int port)
        {
            // starts server and waits for a connection
            try {
                System.out.println("Server");
                server = new ServerSocket(port);
                System.out.println("Server started");
                System.out.println("Waiting for a client ...");

                socket = server.accept();
                System.out.println("Client accepted");
//                 takes input from the client socket
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line = "";

                // reads message from client until "Over" is sent
                while (!line.equals("Over")) {
                    try {
                        line = in.readLine();
                        System.out.println(line);

                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }
                System.out.println("Closing connection");

                // close connection
                socket.close();
                in.close();
            } catch (IOException i) {
                System.out.println(i);
            }
        }
}
