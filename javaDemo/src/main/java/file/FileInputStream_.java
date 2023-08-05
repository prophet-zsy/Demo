package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

public class FileInputStream_ {
    public static void main(String[] args) {
        m1();
        m2();
    }

    private static void m1 () {
        String filePath = "./test1.txt";
        FileInputStream fileInputStream = null;
        int readData = 0;
        try {
            fileInputStream = new FileInputStream(filePath);
//            使用字节流读取文本文件时，当对其进行显示时，并不清楚应该在哪些位置截断，从而导致有可能出现乱码
            while ((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
    private static void m2 () {
        String filePath = "./test1.txt";
        FileInputStream fileInputStream = null;
        byte[] readData = new byte[3];
        int readLen = 0;
        try {
            fileInputStream = new FileInputStream(filePath);
//            使用字节流读取文本文件时，当对其进行显示时（尤其多个字节表示一个字符时），并不清楚应该在哪些位置截断，从而导致有可能出现乱码
//            这时字节流虽然可以操作文本文件，但是文本文件内部结构字节流是不知悉的，因此，只可以使用字节流来进行复制等内容无关的操作！！！
            while ((readLen = fileInputStream.read(readData)) != -1) {
                System.out.print(new String(readData, 0, readLen));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
