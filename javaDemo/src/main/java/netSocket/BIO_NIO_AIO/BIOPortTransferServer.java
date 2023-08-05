package netSocket.BIO_NIO_AIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/*
    接收客户端发来的信息，
    并将其转发给所有的在线客户端
 */


public class BIOPortTransferServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(22222);
            while (true) {
                Socket socket = serverSocket.accept();
                new MyThread3(socket).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread3 extends Thread {
    private static List<Socket> allSocketOnLine = new ArrayList<>();
    private Socket socket;

    public MyThread3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        synchronized (allSocketOnLine) {
            allSocketOnLine.add(socket);
        }
        System.out.println("服务端收到新连接");
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String content = bufferedReader.readLine();
                synchronized (allSocketOnLine) {
                    sendMSGToAllClient(content);
                }
                System.out.println("服务端收到" + content + "并将其转发至所有客户端");
            }
        } catch (IOException e) {
            synchronized (allSocketOnLine) {
                allSocketOnLine.remove(socket);
            }
            System.out.println("当前有人下线了");
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMSGToAllClient(String content) {
        for (Socket socket : allSocketOnLine) {
            OutputStream outputStream = null;
            BufferedWriter bufferedWriter = null;
            try {
                outputStream = socket.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(content);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
//                try {
////                    socket.shutdownOutput();  //关闭socket对应的输入流或输出流之后，便无法再打开了，是一次性的
////                    bufferedWriter.close(); // 任何关闭对应流的操作都会造成对应socket的关闭
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

}

