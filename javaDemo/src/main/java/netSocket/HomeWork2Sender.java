package netSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class HomeWork2Sender {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        BufferedReader bufferedReader = null;
        try {
            datagramSocket = new DatagramSocket(7777);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = bufferedReader.readLine();
                DatagramPacket datagramPacket = new DatagramPacket(input.getBytes(), 0, input.getBytes().length, InetAddress.getLocalHost(), 7776);
                datagramSocket.send(datagramPacket);
                byte[] buf = new byte[1024];
                DatagramPacket datagramPacket1 = new DatagramPacket(buf, 0, buf.length);
                datagramSocket.receive(datagramPacket1);
                System.out.println(new String(datagramPacket1.getData(), 0, datagramPacket1.getLength()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}
