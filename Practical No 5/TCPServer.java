import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000); // Listen on port 5000
        System.out.println("Server started. Waiting for client...");

        Socket socket = server.accept(); // Accept client connection
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String msg = in.readLine(); // Receive message from client
        System.out.println("Client says: " + msg);

        out.println("Hello Client, message received!"); // Reply to client

        socket.close();
        server.close();
    }
}
