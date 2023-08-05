package file;

import java.io.*;

public class Homework2 {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("./test1.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./test2.txt"), "GBK"));
        String line = "";
        int lineNum = 0;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(String.valueOf(++lineNum) + " " + line);
        }
        bufferedReader.close();

    }
}
