package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Propertires1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./test1.txt"));
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
            String[] split = line.split("=");
            System.out.println(split[0] + "是" + split[1]);
        }
        System.out.println("读取文件成功");
    }
}
