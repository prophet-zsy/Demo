package file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintWriter_ {
    public static void main(String[] args) throws FileNotFoundException {
//        PrintWriter printWriter = new PrintWriter(System.out);
        PrintWriter printWriter = new PrintWriter("./test1.txt");
        printWriter.println("这里测试printWriter");
        printWriter.close();
    }
}
