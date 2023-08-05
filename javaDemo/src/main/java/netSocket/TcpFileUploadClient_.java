package netSocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpFileUploadClient_ {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("./1.jpg"));
        byte[] bytes = StreamUtils_.streamToByteArray(bufferedInputStream);
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
//        Thread.sleep(30000);  // 测试 netstat命令
        socket.shutdownOutput();
        outputStream.flush();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        outputStream.close();
        socket.close();
        bufferedInputStream.close();
    }
}
