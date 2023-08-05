package netSocket.BIO_NIO_AIO.BIOChat;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BIOChatClient {
    private static JFrame loginFrame;
    private static JFrame chatFrame;
    // 登陆界面用户信息
    private static JTextField usrName;

    // 接收消息的界面
    private static JTextArea msgShow;
    private static JScrollPane scrollMsgShow;
    private static JTextArea onLineUsers;

    private static Socket socket = null;
    private static OutThread outThread = null;
    private static InThread inThread = null;

    // 首次登录确认窗口构建完成，再显示消息
    private static final Object displayChatFrameFinished = new Object();

    private static void displayLoginFrame () {
        loginFrame = new JFrame();
        loginFrame.setSize(600, 400);
        JPanel container = new JPanel();
        container.add(new JLabel("IP地址"));
        container.add(new JTextField(16));
        JPanel container2 = new JPanel();
        container2.add(new JLabel("用户名"));
        usrName = new JTextField(16);
        container2.add(usrName);
        JPanel container3 = new JPanel();
        JButton cancel = new JButton("取消");
        JButton ensure = new JButton("确定");
        container3.add(cancel);
        container3.add(ensure);
        Box box = Box.createVerticalBox();
        box.add(new JPanel());
        box.add(container);
        box.add(container2);
        box.add(container3);
        loginFrame.setContentPane(box);
        loginFrame.setTitle("登陆群聊");
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);

        ensure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectServer();
                loginFrame.dispose();
                displayChatFrame();
            }
        });
    }

    private static void displayChatFrame () {
        chatFrame = new JFrame();
        chatFrame.setSize(800, 600);

        Box box1 = Box.createVerticalBox();
        msgShow = new JTextArea(23, 50);
        JTextArea msgSend = new JTextArea(4, 50);
        msgShow.setBackground(Color.lightGray);
        msgSend.setBackground(Color.white);
        msgShow.setFont(new Font("宋体",Font.PLAIN,16));
        msgSend.setFont(new Font("宋体",Font.PLAIN,20));
        scrollMsgShow = new JScrollPane();
        scrollMsgShow.setBounds(0, 0, 600, 400);
        scrollMsgShow.setViewportView(msgShow);
        box1.add(scrollMsgShow);
        box1.add(msgSend);
        Box box2 = Box.createVerticalBox();
        onLineUsers = new JTextArea(4, 10);
        onLineUsers.setBackground(Color.gray);
        onLineUsers.setFont(new Font("黑体",Font.BOLD,24));
        box2.add(onLineUsers);
        Box box22 = Box.createHorizontalBox();
        JButton jButton = new JButton("发送");
        JCheckBox jCheckBox = new JCheckBox("私聊");
        box22.add(jButton);
        box22.add(jCheckBox);
        box2.add(box22);

        Box box = Box.createHorizontalBox();
        box.add(box1);
        box.add(box2);
        chatFrame.setContentPane(box);
        chatFrame.setTitle("Happy Chat (" + usrName.getText() + ")");
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setVisible(true);

        // 发送
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (jCheckBox.isSelected())
//                    outThread.flag = 3;  // 私聊
//                else
                    outThread.flag = 1;
                outThread.content = msgSend.getText();
                outThread.run();
                msgSend.setText("");
            }
        });
        msgSend.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
//                    if (jCheckBox.isSelected())
//                        outThread.flag = 3;  // 私聊
//                    else
                        outThread.flag = 1;
                    outThread.content = msgSend.getText();
                    outThread.run();
                    msgSend.setText("");
                }
            }
        });

        // 聊天窗口完成构建，通知消息接收线程，可以开始设置内容了
        synchronized (displayChatFrameFinished) {
            displayChatFrameFinished.notify();
        }
    }

    private static void connectServer () {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 22222);
            outThread = new OutThread(socket);
            inThread = new InThread(socket);
//            outThread.start();
            inThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        outThread.flag = 0;
        outThread.content = usrName.getText();
        outThread.run();
    }

    private static void joinSelf() {
        try {
//            outThread.join();
            inThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        displayLoginFrame();
    }

    // 实际上发送消息在主线程中运行即可，因此这里不启动该线程，只同步运行run函数
    static class OutThread extends Thread {
        private Socket socket;
        private OutputStream outputStream = null;
        private DataOutputStream dataOutputStream = null;

        public int flag;
        public String content;

        public OutThread(Socket socket) {
            this.socket = socket;
            try {
                synchronized (socket) {
                    outputStream = socket.getOutputStream();
                }
                dataOutputStream = new DataOutputStream(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                dataOutputStream.writeInt(flag);
                dataOutputStream.writeUTF(content);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protected void finalize() {
//            try {
//                dataOutputStream.close();
//                socket.shutdownOutput();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class InThread extends Thread {
        private Socket socket;

        public InThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            DataInputStream dataInputStream = null;
            try {
                synchronized (socket) {
                    inputStream = socket.getInputStream();
                }
                dataInputStream = new DataInputStream(inputStream);
                while (true) {
                    int flag = dataInputStream.readInt();
                    String content = dataInputStream.readUTF();
                    switch (flag) {
                        case 0:
                            if (onLineUsers == null) {
                                synchronized (displayChatFrameFinished) {
                                    displayChatFrameFinished.wait();
                                }
                            }
                            onLineUsers.setText(content);
                            break;
                        default:
                            msgShow.setText(content);
                            JScrollBar jScrollBar = scrollMsgShow.getVerticalScrollBar();
                            jScrollBar.setValue(jScrollBar.getMaximum());
                            break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
//                try {
//                    dataInputStream.close();
//                    socket.shutdownInput();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}

