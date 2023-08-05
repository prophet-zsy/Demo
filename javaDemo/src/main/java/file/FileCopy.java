package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        copy();
        long endTime = System.currentTimeMillis();
        System.out.println("拷贝用时: " + (endTime - startTime) + "ms");
    }

    private static void copy () {
        String srcFilePath = "./1.jpg";
        String destFilePath = "./test/1.jpg";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
//        int content = 0;
        byte[] content = new byte[32]; // 这里可以尝试不同的byte数组大小，拷贝所花的时间
        int contentLen = 0;
        try {
            fileInputStream = new FileInputStream(srcFilePath);
            fileOutputStream = new FileOutputStream(destFilePath);
            while ((contentLen = fileInputStream.read(content)) != -1) {
                fileOutputStream.write(content, 0, contentLen);
            }
            System.out.println("文件拷贝完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
