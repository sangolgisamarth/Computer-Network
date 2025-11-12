import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Step 1: Create ServerSocket
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for client...");

            // Step 2: Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Step 3: Create input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Step 4: Say Hello to each other
            String clientMessage = in.readLine();
            System.out.println("Client says: " + clientMessage);
            out.println("Hello from Server!");

            // Step 5: File transfer section
            String fileName = in.readLine();  // receive filename
            System.out.println("Receiving file: " + fileName);

            FileOutputStream fos = new FileOutputStream("received_" + fileName);
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                if (bytesRead < 4096) break;  // end of file
            }

            fos.close();
            System.out.println("File received successfully!");

            // Step 6: Close connections
            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
