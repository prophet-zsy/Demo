package netSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpReceiverB_ {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, 0, buf.length);
        datagramSocket.receive(datagramPacket);
//        datagramPacket.getData()可以用buf替代，为了整齐，还是调用函数吧
        System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));

        buf = "好的，明天见".getBytes();
        datagramPacket = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName("192.168.147.1"), 9999);
        datagramSocket.send(datagramPacket);
        System.out.println("B发送成功");
        datagramSocket.close();
    }
}
