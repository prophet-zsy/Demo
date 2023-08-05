package netSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient_ {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("hello server".getBytes());
        Thread.sleep(30000);
        outputStream.close();
        socket.close();
        System.out.println("客户端退出了");
    }
}
