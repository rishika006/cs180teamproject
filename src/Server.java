import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * the basics to create a server 
 *
 * @author Rishitha Adusumilli and Shaivi Mishra
 *
 * @version 11/27/2024
 *
 */

public class Server {


    public static void main(String[] args) {
        int port = 4242; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Create a new thread with ClientHandler runnable
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start(); // Start the thread
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}




