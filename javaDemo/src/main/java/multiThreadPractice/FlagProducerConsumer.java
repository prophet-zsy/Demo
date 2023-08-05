package multiThreadPractice;

public class FlagProducerConsumer {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

class Player extends Thread {
    private TV tv;
    Player(TV tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 0) {
                this.tv.play("射雕英雄传");
            } else {
                this.tv.play("天天向上");
            }
        }
    }
}

class Watcher extends Thread {
    private TV tv;
    Watcher(TV tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.tv.watch();
        }
    }
}

class TV {
    private String channel;
    private boolean haveChannel = false;

    public synchronized void play(String channel) {
        if (haveChannel) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.channel = channel;
        System.out.println("演员表演了" + channel);
        haveChannel = !haveChannel;
        this.notifyAll();
    }

    public synchronized void watch () {
        if (! haveChannel) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String channel = this.channel;
        System.out.println("观众观看了" + channel);
        haveChannel = !haveChannel;
        this.notifyAll();
    }
}

