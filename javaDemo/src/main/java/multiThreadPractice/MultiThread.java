package multiThreadPractice;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MultiThread extends Thread {

    private String url;
    private String name;

    public MultiThread(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
//        multiThreadPractice.MultiThread multiThread = new multiThreadPractice.MultiThread();
//        multiThread.start();
//        for (int i = 0; i < 200 ; i ++) {
//            System.out.println("我在学习多线程" + i);
//        }
        MultiThread t1 = new MultiThread("https://tse2-mm.cn.bing.net/th/id/OIP-C.9R1wNwNykWpT354MzvWMEAHaKu?w=182&h=264&c=7&r=0&o=5&dpr=1.25&pid=1.7", "1.jpg");
        MultiThread t2 = new MultiThread("https://tse4-mm.cn.bing.net/th/id/OIP-C.x7K_021faU5CcmeGMMOJIAHaM0?w=182&h=314&c=7&r=0&o=5&dpr=1.25&pid=1.7", "2.jpg");
        MultiThread t3 = new MultiThread("https://tse1-mm.cn.bing.net/th/id/OIP-C.Sw0NNdLQUKEgE7CD1ICRDQHaLG?w=182&h=273&c=7&r=0&o=5&dpr=1.25&pid=1.7", "3.jpg");

        t1.start();
        t2.start();
        t3.start();

    }

    @Override
    public void run() {
//        for (int i = 0; i < 20; i ++) {
//            System.out.println("我在看代码" + i);
//        }
        ImgDownLoader.download(url, name);
        System.out.println("下载了" + name);
    }
}

class ImgDownLoader {
    public static void download (String url, String file) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

