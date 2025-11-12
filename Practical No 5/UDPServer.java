import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(5000); // Listen on port 5000
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("UDP Server started. Waiting for client...");

        // Receive packet from client
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Client says: " + clientMessage);

        // Prepare response
        String reply = "Hello Client, message received!";
        sendData = reply.getBytes();

        InetAddress clientAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();

        // Send response to client
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        serverSocket.send(sendPacket);

        System.out.println("Reply sent to client.");

        serverSocket.close();
    }
}
