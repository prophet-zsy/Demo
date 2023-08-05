package file;

import java.io.File;

public class FileInfo {
    public static void main(String[] args) {
        info();
    }

    private static void  info () {
        File file = new File("./test1.txt");
        System.out.println("file name = " + file.getName());
        System.out.println("absolute name = " + file.getAbsolutePath());
        System.out.println("file parent path = " + file.getParent());
        System.out.println("file size (byte) = " + file.length());
        System.out.println("file exists = " + file.exists());
        System.out.println("file is dir = " + file.isDirectory());
        System.out.println("file id file = " + file.isFile());
    }
}
