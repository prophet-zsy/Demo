package netSocket.BIO_NIO_AIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BIOServerThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(3, 3, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(22222);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new MyThread2(socket));
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

class MyThread2 implements Runnable {
    private Socket socket;

    public MyThread2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("服务端收到新连接");
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                System.out.println("服务端收到" + bufferedReader.readLine());
            }
        } catch (IOException e) {
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
}

