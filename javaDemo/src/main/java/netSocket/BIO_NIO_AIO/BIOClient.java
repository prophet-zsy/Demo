package netSocket.BIO_NIO_AIO;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BIOClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 22222);
            outputStream = socket.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("请输入：");
                String content = scanner.nextLine();
                bufferedWriter.write(content);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
