package file;

import java.io.File;
import java.io.IOException;

public class FileIOCreate {
    public static void main(String[] args) {
        create01();
        create02();
        create03();
    }

    private static void create01 () {
        File file = new File("./test1.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("创建文件成功");
    }
    private static void create02 () {
        File dir = new File("./test");
        File file = new File(dir, "test2.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("创建文件成功");
    }
    private static void create03 () {
        String dir = "./test";
        File file = new File(dir, "test3.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("创建文件成功");
    }
}
