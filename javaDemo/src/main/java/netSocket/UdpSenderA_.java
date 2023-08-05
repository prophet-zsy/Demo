package netSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpSenderA_ {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        byte[] buf = "走呀，明天去吃火锅~".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName("192.168.147.1"), 9998);
        datagramSocket.send(datagramPacket);
        System.out.println("A发送成功");

        buf = new byte[1024];
        datagramPacket = new DatagramPacket(buf, 0, buf.length);
        datagramSocket.receive(datagramPacket);
//        datagramPacket.getData()可以用buf替代，为了整齐，还是调用函数吧
        System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
        datagramSocket.close();
    }
}
