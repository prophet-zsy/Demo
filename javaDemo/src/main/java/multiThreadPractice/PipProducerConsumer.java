package multiThreadPractice;

public class PipProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        new Thread(new Producer(buffer)).start();
        new Thread(new Consumer(buffer)).start();
    }
}


//管程法，生产者与消费者共享同一个缓冲区，notify和wait作用在同一个缓冲区中
//只适合只有一个生产者和只有一个消费者的情况

class Chicken {
    private int id;

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Buffer {
    private Chicken[] buf = new Chicken[10];
    private int count = 0;

    public synchronized void push (Chicken chicken) {
        if (count == buf.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buf[count++] = chicken;
        System.out.println("生产了第" + chicken.getId() + "只鸡");
        this.notifyAll();
    }

    public synchronized Chicken pop () {
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Chicken res = buf[--count];
        System.out.println("消费了第" + res.getId() + "只鸡");
        this.notifyAll();
        return res;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            buffer.push(new Chicken(i));
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chicken chicken = buffer.pop();
        }
    }
}