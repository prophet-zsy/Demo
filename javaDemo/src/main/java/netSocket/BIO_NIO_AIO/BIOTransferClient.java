package netSocket.BIO_NIO_AIO;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


/*
    发送消息给服务端，
    并接收其转发来的消息
 */

public class BIOTransferClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 22222);
            netSocket.BIO_NIO_AIO.OutThread outThread = new netSocket.BIO_NIO_AIO.OutThread(socket);
            netSocket.BIO_NIO_AIO.InThread inThread = new netSocket.BIO_NIO_AIO.InThread(socket);
            outThread.start();
            inThread.start();
            outThread.join();
            inThread.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class OutThread extends Thread {
    private Socket socket;

    public OutThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            synchronized (socket) {
                outputStream = socket.getOutputStream();
            }
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("------------请输入：");
                String content = scanner.nextLine();
                bufferedWriter.write(content);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                bufferedWriter.close();
                socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class InThread extends Thread {
    private Socket socket;

    public InThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            synchronized (socket) {
                inputStream = socket.getInputStream();
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                bufferedReader.close();
                socket.shutdownInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}