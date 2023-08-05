package netSocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HomeWork3Client {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入要下载的文件名：");
        Scanner scanner = new Scanner(System.in);
        String flieName = scanner.next();
        Socket socket = new Socket(InetAddress.getLocalHost(), 55555);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream1 = new FileOutputStream("./test/" + flieName);
//        告知服务端文件名
        outputStream.write(flieName.getBytes());
        socket.shutdownOutput();
        outputStream.flush();
//        从服务端获取文件
        byte[] bytes = StreamUtils_.streamToByteArray(inputStream);
//        写入本地文件
        outputStream1.write(bytes);
        outputStream1.flush();
        outputStream1.close();
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
