package netSocket.BIO_NIO_AIO.NIOChat;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NIOChatServer {
    public static final String SPLITOR = "!@#%&_"; //定义包头，用来分割数据流，避免粘包问题
    private static Selector selector = null;
    private static ServerSocketChannel serverSocketChannel = null;

    public static void main(String[] args) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 22222));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            new MyThread4(serverSocketChannel).run();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                serverSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class MyThread4 extends Thread {
        private static Map<SocketChannel, String> allSocketOnLine = new HashMap<>();
        private static String msgHistory = "";
        private ServerSocketChannel serverSocketChannel;

        public MyThread4(ServerSocketChannel serverSocketChannel) {
            this.serverSocketChannel = serverSocketChannel;
        }

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            readContent(socketChannel);
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
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

        private void readContent(SocketChannel socketChannel) {
            try {
                String content = readFromChannel(socketChannel);
                onReceive(content, socketChannel);
            } catch (IOException e) {
                synchronized (allSocketOnLine) {
                    allSocketOnLine.remove(socketChannel);
                }
                String msg = writeConcactsMSG();
                sendMSGToAllClient(0, msg);
                System.out.println("当前有人下线了");
                try {
                    socketChannel.socket().close();
                    socketChannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
//                e.printStackTrace();
            }
        }

        private boolean onReceive(String content, SocketChannel socketChannel) throws IOException {
            String[] strs = content.split(SPLITOR);  // 对收到的消息做切分，防止粘包
            // i = 1 是因为strs中第一项为空，SPLITOR打头的字符串，默认把第一项为空
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                int flag = Integer.parseInt(str.substring(0, 1));
                str = str.substring(1);
                //  根据不同flag做不同处理
                switch (flag) {
                    case 0: // 登陆
                        synchronized (allSocketOnLine) {
                            allSocketOnLine.put(socketChannel, str);
                        }
                        String msg = writeConcactsMSG();
                        sendMSGToAllClient(0, msg);
                        sendMSGToAllClient(1, msgHistory);
                        System.out.println(str + "登陆了" + socketChannel.getRemoteAddress());
                        break;
                    case 1: // 群发
                        String usrName = allSocketOnLine.get(socketChannel);
                        updateMSGHistory(1, usrName, str);
                        sendMSGToAllClient(1, msgHistory);
                        System.out.println("服务端收到" + str + "并将其转发至所有客户端");
                        break;
                    case 2: // @某人 // 需要变动客户端的onLineUsers控件，尚未实现
                        //                                    String atName = dataInputStream.readUTF();
                        //                                    content = "@" + atName + " " + content;
                        sendMSGToAllClient(2, str);
                        System.out.println("服务端收到" + str + "并将其转发至所有客户端");
                        break;
                    case 3: // 私聊  // 需要变动客户端的onLineUsers控件，尚未实现
                        //                                    String destName = dataInputStream.readUTF();
                        //                        content =
                        //                                    sendMSGToONEClient(3, content, destName);
                        break;
                }
            }
            return true;
        }

        private String readFromChannel(SocketChannel socketChannel) throws IOException {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.clear();
            socketChannel.read(byteBuffer);
            byte[] bytes = new byte[1024];
            byteBuffer.flip();
            byteBuffer.get(bytes, 0, byteBuffer.limit());
            String content = new String(bytes, 0, byteBuffer.limit());
            return content;
        }

        private boolean writeIntoChannel(SocketChannel socketChannel, String content) throws IOException {
            content = SPLITOR + content;
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.clear();
            byteBuffer.put(content.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            return true;
        }

        /**
         * 更新消息历史记录
         *
         * @param flag
         * @param content
         */
        private void updateMSGHistory(int flag, String usrName, String content) {
            synchronized (msgHistory) {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
         *
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
         *
         * @param flag
         * @param content
         */
        private void sendMSGToAllClient(int flag, String content) {
            content = flag + content;
            for (SocketChannel socketChannel : allSocketOnLine.keySet()) {
                try {
                    writeIntoChannel(socketChannel, content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /***
         * 发消息给单独用户
         * @param flag
         * @param content
         */
        private void sendMSGToONEClient(int flag, String content, String destName) {
            content = flag + content;
            for (SocketChannel socketChannel : allSocketOnLine.keySet()) {
                if (allSocketOnLine.get(socketChannel).equals(destName)) {
                    try {
                        writeIntoChannel(socketChannel, content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}



