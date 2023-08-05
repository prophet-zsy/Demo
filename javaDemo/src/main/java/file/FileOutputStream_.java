package file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream_ {
    public static void main(String[] args) {
        m1();
    }
    private static void m1 () {
        String filePath = "./test1.txt";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//            FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
            fileOutputStream.write('H');

            fileOutputStream.write("hello, world\n".getBytes());

            fileOutputStream.write("hello, world\n".getBytes(), 3, 6);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
