package file;

import java.io.File;

public class FileDel {
    public static void main(String[] args) {
        m1();
        m2();
        m3();
    }
    private static void m1 () {
        String filePath = "./test1.txt";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete())
                System.out.println("del success");
            else
                System.out.println("del failed");
        } else {
            System.out.println("file not exists");
        }
    }
    private static void m2 () {
        String filePath = "./test";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete())
                System.out.println("del success");
            else
                System.out.println("del failed");
        } else {
            System.out.println("dir not exists");
        }
    }
    private static void m3 () {
        String filePath = "./test/test/test/test";
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("file exists");
        } else {
            if (file.mkdirs()) {
                System.out.println("create success");
            } else {
                System.out.println("create failed");
            }
        }
    }
}
