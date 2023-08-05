package file;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriter_ {
    public static void main(String[] args) {
        m1();
    }
    private static void m1 () {
        String filePath = "./test1.txt";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
            fileWriter.write('风');
            fileWriter.write("风雨之后必见彩虹", 5, 3);
            fileWriter.write("风雨之后必见彩虹");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
                System.out.println("文件写成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
