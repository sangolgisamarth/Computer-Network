import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000); // Connect to server (use IP if on another PC)
        System.out.println("Connected to server.");

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.print("Enter message for server: ");
        String msg = input.readLine(); // Take user input

        out.println(msg); // Send to server
        System.out.println("Server reply: " + in.readLine()); // Read server reply

        socket.close();
    }
}
