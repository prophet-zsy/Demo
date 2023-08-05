package file;

import java.io.IOException;
import java.io.PrintStream;

public class PrintStream_ {
    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;
        printStream.println("输出测试");

        printStream.write("输出测试\n".getBytes());
        printStream.close();

        System.setOut(new PrintStream("./test1.txt"));
        System.out.println("测试将System.out重定向至文件");
    }
}
