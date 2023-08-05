package netSocket.BIO_NIO_AIO.BIOChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BIOChatServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(22222);
            while (true) {
                Socket socket = serverSocket.accept();
                new MyThread4(socket).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                serverSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}

class MyThread4 extends Thread {
    private static Map<Socket, String> allSocketOnLine = new HashMap<>();
    private static String msgHistory = "";
    private Socket socket;
    private String usrName = "";

    public MyThread4(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        try {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            while (true) {
                int flag = dataInputStream.readInt();
                String content = dataInputStream.readUTF();
                switch (flag) {
                    case 0: // 登陆
                        usrName = content;
                        synchronized (allSocketOnLine) {
                            allSocketOnLine.put(socket, usrName);
                        }
                        String msg = writeConcactsMSG();
                        sendMSGToAllClient(0, msg);
                        sendMSGToAllClient(1, msgHistory);
                        System.out.println(usrName + "登陆了" + socket.getRemoteSocketAddress());
                        break;
                    case 1: // 群发
                        updateMSGHistory(1, content);
                        sendMSGToAllClient(1, msgHistory);
                        System.out.println("服务端收到" + content + "并将其转发至所有客户端");
                        break;
                    case 2: // @某人 // 需要变动客户端的onLineUsers控件，尚未实现
                        String atName = dataInputStream.readUTF();
                        content = "@" + atName + " " + content;
                        sendMSGToAllClient(2, content);
                        System.out.println("服务端收到" + content + "并将其转发至所有客户端");
                        break;
                    case 3: // 私聊  // 需要变动客户端的onLineUsers控件，尚未实现
                        String destName = dataInputStream.readUTF();
//                        content =
                        sendMSGToONEClient(3, content, destName);
                        break;
                }
            }
        } catch (IOException e) {
            synchronized (allSocketOnLine) {
                allSocketOnLine.remove(socket);
            }
            String msg = writeConcactsMSG();
            sendMSGToAllClient(0, msg);
            System.out.println("当前有人下线了");
            e.printStackTrace();
        } finally {
//            try {
//                dataInputStream.close();
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * 更新消息历史记录
     * @param flag
     * @param content
     */
    private void updateMSGHistory(int flag, String content) {
        synchronized (msgHistory) {
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String timeStamp = dateFormat.format(date);
            msgHistory += usrName + "  ";
            msgHistory += timeStamp;
            msgHistory += "\n";
            msgHistory += content;
            msgHistory += "\n";
        }
    }

    /**
     * 获取所有在线用户的名字，
     * 并转换为消息
     * @return
     */
    private String writeConcactsMSG() {
        String msg = "";
        for (String name : allSocketOnLine.values()) {
            msg += name;
            msg += "\n";
        }
        return msg;
    }

    /**
     * 群发消息给所有在线用户
     * @param flag
     * @param content
     */
    private void sendMSGToAllClient(int flag, String content) {
        synchronized (allSocketOnLine) {
            for (Socket socket : allSocketOnLine.keySet()) {
                OutputStream outputStream = null;
                DataOutputStream dataOutputStream = null;
                try {
                    outputStream = socket.getOutputStream();
                    dataOutputStream = new DataOutputStream(outputStream);
                    dataOutputStream.writeInt(flag);
                    dataOutputStream.writeUTF(content);
                    dataOutputStream.flush();
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

    /***
     * 发消息给单独用户
     * @param flag
     * @param content
     */
    private void sendMSGToONEClient(int flag, String content, String destName) {
        synchronized (allSocketOnLine) {
            for (Socket socket : allSocketOnLine.keySet()) {
                if (allSocketOnLine.get(socket).equals(destName)) {
                    OutputStream outputStream = null;
                    DataOutputStream dataOutputStream = null;
                    try {
                        outputStream = socket.getOutputStream();
                        dataOutputStream = new DataOutputStream(outputStream);
                        dataOutputStream.writeInt(flag);
                        dataOutputStream.writeUTF(content);
                        dataOutputStream.flush();
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
    }

}

