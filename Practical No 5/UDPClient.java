import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost"); // Use IP if on another PC

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        byte[] sendData;
        byte[] receiveData = new byte[1024];

        System.out.print("Enter message for server: ");
        String message = userInput.readLine();

        sendData = message.getBytes();

        // Send message to server
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 5000);
        clientSocket.send(sendPacket);

        // Receive response
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String serverReply = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Server reply: " + serverReply);

        clientSocket.close();
    }
}
