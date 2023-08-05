package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriter_ {
    public static void main(String[] args) throws IOException {
        String filePath = "./test1.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write("aadsfqwer");
        bufferedWriter.newLine();
        bufferedWriter.write("aadsfqwer");
        bufferedWriter.newLine();
        bufferedWriter.write("aadsfqwer");
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
