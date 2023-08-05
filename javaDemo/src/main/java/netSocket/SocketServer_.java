package netSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SocketServer_ {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("server在端口9999监听，等待连接中");
        Socket socket = serverSocket.accept();
        System.out.println("socket = " + socket.getClass());
        InputStream inputStream = socket.getInputStream();
        System.out.println("socket 开始接收数据");
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println("server端收到" + new String(buf, 0, readLen));  // 这里直接输出字节流读取到的内容有点危险！
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
