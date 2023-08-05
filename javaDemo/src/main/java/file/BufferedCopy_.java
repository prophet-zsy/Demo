package file;

import java.io.*;

public class BufferedCopy_ {
    public static void main(String[] args) throws IOException {
//        不要用字符流操作二进制文件【声音、视频、doc、pdf等等】，可能会造成文件损坏
//        String br = "./1.jpg";
//        String bw = "./11.jpg";
        String br = "./test1.txt";
        String bw = "./test/test2.txt";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(br));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(bw));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
