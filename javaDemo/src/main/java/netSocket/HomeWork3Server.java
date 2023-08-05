package netSocket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HomeWork3Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(55555);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
//        从客户端取得文件名
        byte[] buf = new byte[1024];
        int readLen = 0;
        String fileName = "";
        while ((readLen = inputStream.read(buf)) != -1) {
            fileName += new String(buf, 0, readLen);
        }
//        将对应文件传给客户端
        String srcFileName = "";
        if ("2.jpg".equals(fileName)) {
            srcFileName = "2.jpg";
        } else
            srcFileName = "3.jpg";
        InputStream inputStream1 = new FileInputStream(srcFileName);
        byte[] bytes = StreamUtils_.streamToByteArray(inputStream1);
        outputStream.write(bytes);
        outputStream.flush();
        inputStream1.close();
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
