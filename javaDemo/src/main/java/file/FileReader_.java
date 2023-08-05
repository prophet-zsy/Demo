package file;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReader_ {
    public static void main(String[] args) {
        m1();
    }
    private static void m1 () {
        String filePath = "./test1.txt";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            int content = 0;
            while ((content = fileReader.read()) != -1) {
                char res = (char) content;
                System.out.print(res);
            }
            System.out.println();
            System.out.println("文件读取成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
