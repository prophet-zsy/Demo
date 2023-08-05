package netSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServe3_ {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("server在端口9999监听，等待连接中");
        Socket socket = serverSocket.accept();
        System.out.println("socket = " + socket.getClass());
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println("socket 开始接收数据");
        System.out.println(bufferedReader.readLine());
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello client");
        bufferedWriter.newLine();
        bufferedWriter.flush();  // 使用字符流，一定要flush
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
