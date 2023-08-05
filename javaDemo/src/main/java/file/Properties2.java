package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Properties2 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./test1.txt"));
        properties.load(bufferedReader);
        properties.list(System.out);
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.getProperty("ip"));
        bufferedReader.close();
    }
}
