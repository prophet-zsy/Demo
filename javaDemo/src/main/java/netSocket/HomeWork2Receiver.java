package netSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HomeWork2Receiver {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(7776);
            while (true) {
                byte[] buf = new byte[1024];
                DatagramPacket datagramPacket1 = new DatagramPacket(buf, 0, buf.length);
                datagramSocket.receive(datagramPacket1);
                String response;
                System.out.println(new String(datagramPacket1.getData(), 0, datagramPacket1.getLength()));
                if ("四大名著是哪些".equals(new String(datagramPacket1.getData(), 0, datagramPacket1.getLength()))) {
                    response = "四大名著是《红楼梦》、《三国演义》、《西游记》、《水浒传》";
                } else
                    response = "What?";
                DatagramPacket datagramPacket = new DatagramPacket(response.getBytes(), 0, response.getBytes().length, InetAddress.getLocalHost(), 7777);
                datagramSocket.send(datagramPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}
