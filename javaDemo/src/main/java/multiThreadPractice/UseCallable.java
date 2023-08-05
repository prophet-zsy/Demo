package multiThreadPractice;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class UseCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public UseCallable() {
    }

    public UseCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable t1 = new UseCallable("https://tse2-mm.cn.bing.net/th/id/OIP-C.9R1wNwNykWpT354MzvWMEAHaKu?w=182&h=264&c=7&r=0&o=5&dpr=1.25&pid=1.7", "1.jpg");
        UseCallable t2 = new UseCallable("https://tse4-mm.cn.bing.net/th/id/OIP-C.x7K_021faU5CcmeGMMOJIAHaM0?w=182&h=314&c=7&r=0&o=5&dpr=1.25&pid=1.7", "2.jpg");
        UseCallable t3 = new UseCallable("https://tse1-mm.cn.bing.net/th/id/OIP-C.Sw0NNdLQUKEgE7CD1ICRDQHaLG?w=182&h=273&c=7&r=0&o=5&dpr=1.25&pid=1.7", "3.jpg");

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Boolean> r1 = executorService.submit(t1);
        Future<Boolean> r2 = executorService.submit(t2);
        Future<Boolean> r3 = executorService.submit(t3);
        System.out.println(r1.get());
        System.out.println(r2.get());
        System.out.println(r3.get());
        executorService.shutdown();
    }

    @Override
    public Boolean call() {
        ImgDownLoader.download(url, name);
        System.out.println("下载了" + name);
        return true;
    }
}






class ImgDownLoader3 {
    public static void download (String url, String file) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}