package file;

import java.io.*;

public class InputStreamReader_ {
    public static void main(String[] args) throws IOException {
        String filePath = "./test1.txt";
        String charSet = "GBK";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charSet));
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
        System.out.println("以" + charSet + "的编码读取文件成功");
    }
}
