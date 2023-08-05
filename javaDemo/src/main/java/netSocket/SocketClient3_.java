package netSocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient3_ {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        System.out.println("客户端发送数据");
        bufferedWriter.write("hello server");
        bufferedWriter.newLine();
        bufferedWriter.flush();  // 使用字符流，一定要flush
        System.out.println("客户端发送数据完成");
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
        System.out.println("客户端退出了");
    }
}
