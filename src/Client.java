import java.io.*;
import java.net.Socket;
import javax.swing.*;

/**
 * CS180 Team Project -- Direct Messaging Platform
 *
 * Creates the client with connection to the server.
 * The Client class also starts the connection to GUI pages.
 *
 * @author Nicholas Chong
 *
 * @version 11/27/2024
 *
 */

public class Client {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader bfr;

    // constructor
    public Client(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        output = new PrintWriter(socket.getOutputStream(), true);
        bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        new MainAuthPage(this);
    }


    // getter methods
    public PrintWriter getOutput() { return output; }
    public BufferedReader getBfr() { return bfr; }


    // MAIN METHOD
    public static void main(String[] args) {
        try {
            new Client("localhost", 5252);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to server: " + e.getMessage());
        }
    }


}
