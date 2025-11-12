import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Step 1: Connect to Server
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("Connected to server!");

            // Step 2: Create input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Step 3: Say Hello to each other
            out.println("Hello from Client!");
            String response = in.readLine();
            System.out.println("Server says: " + response);

            // Step 4: File transfer section
            String fileName = "sample.txt"; // file to send
            out.println(fileName);

            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
            fis.close();

            System.out.println("File sent successfully!");

            // Step 5: Close connections
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
