package netSocket;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HomeWork1Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            serverSocket = new ServerSocket(6666);
            socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            while (true) {
                switch (bufferedReader.readLine()) {
                    case "name":
                        bufferedWriter.write("我是nova");
                        break;
                    case "hobby":
                        bufferedWriter.write("编写java程序");
                        break;
                    default:
                        bufferedWriter.write("你说啥呢");
                }
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                bufferedReader.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
