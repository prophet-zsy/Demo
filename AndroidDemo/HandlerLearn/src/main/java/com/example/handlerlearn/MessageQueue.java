package com.example.handlerlearn;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageQueue {

    private BlockingQueue<Message> blockingQueue;

    public MessageQueue() {
        blockingQueue = new LinkedBlockingDeque<>();
    }

    public Message next() {
        Message message = null;
        try {
            message = blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
    public boolean add(Message message) {
        blockingQueue.add(message);
        return true;
    }
}
