package com.example.handlerlearn;


public class Looper {

    private static ThreadLocal<Looper> looper = new ThreadLocal<Looper>();
    public MessageQueue messageQueue;

    private Looper() {
        messageQueue = new MessageQueue();
    }

    public static Looper getMyLoop() {
        if (looper.get() == null) looper.set(new Looper());
        return looper.get();
    }

    public void loop() {
        while (true) {
            Message message = messageQueue.next();
            Handler handler = message.handler;
            handler.handleMessage(message);
        }
    }
}
