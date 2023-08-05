package com.example.handlerlearn;

public class Handler {

    private Looper looper;

    public Handler() {  //  可以获取当前线程的looper
        this.looper = Looper.getMyLoop();
    }
    public Handler(Looper looper) {  // 消费消息的逻辑（handler）可以不在目标线程定义
        this.looper = looper;
    }
    public void handleMessage(Message message){

    }
    public void sendMessage(Message message) {
        message.handler = this;
        this.looper.messageQueue.add(message);
    }
}
