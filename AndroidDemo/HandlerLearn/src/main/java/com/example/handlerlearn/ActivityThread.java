package com.example.handlerlearn;


/**
 * 共四个角色：Looper（一个线程一个）、MessageQueue（一个线程一个）、Handler、Message
 * 当消息未被消费时，会有这样一个引用链：Looper -> MessageQueue -> Message -> Handler -> Looper
 * Looper、MessageQueue的生命周期和对应的线程相同
 * 如果Handler为Activity中的匿名内部类，会隐式持有Activity，因此对应消息未被消费时，会产生内存泄漏的情况：
 * 1、如果主线程（Activity）给子线程发消息，Activity的生命周期会被延长至子线程回收或消息被消费
 * 2、如果子线程给主线程发消息，Activity的生命周期会被延长至消息被消费
 */


public class ActivityThread {
    static Handler subHandler;

    public static void main(String[] args) {
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message message) {
                System.out.println("主线程收到了消息：" + message.msg);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                subHandler = new Handler() {  // 这里handler初始化会获取当前执行线程的looper
                    @Override
                    public void handleMessage(Message message) {
                        System.out.println("子线程收到了消息：" + message.msg);
                    }
                };
                handler.sendMessage(new Message("来自子线程的消息"));
                subHandler.sendMessage(new Message("来自子线程的消息"));
                Looper.getMyLoop().loop();
            }
        }).start();
        handler.sendMessage(new Message("来自主线程的消息"));
        subHandler.sendMessage(new Message("来自主线程的消息"));
        Looper.getMyLoop().loop();
    }
}
