package netSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileUploadServer_ {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = StreamUtils_.streamToByteArray(inputStream);
        FileOutputStream fileOutputStream = new FileOutputStream("./test/1.jpg");
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write("收到图片");
        outputStreamWriter.flush();
        socket.shutdownOutput();
        outputStreamWriter.close();
        fileOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
