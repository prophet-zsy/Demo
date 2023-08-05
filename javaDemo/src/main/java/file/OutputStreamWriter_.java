package file;

import java.io.*;

public class OutputStreamWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "./test2.txt";
        String charSet = "GBK";
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath), charSet);
        outputStreamWriter.write("这里是测试中文");
        outputStreamWriter.close();
        System.out.println("以" + charSet + "的编码写入文件成功");
    }
}
