package file;

import java.io.*;

public class Homework1 {
    public static void main(String[] args) throws IOException {
        File file = new File("./mytemp");
        if (file.exists()) {
            System.out.println("mytemp文件夹已经存在");
        } else {
            if (file.mkdir())
                System.out.println("mytemp文件夹创建成功");
            else
                System.out.println("mytemp文件夹创建失败");
        }
        File file2 = new File("./mytemp/hello.txt");
        if (file2.exists()) {
            System.out.println("hello.txt文件已经存在于mytemp文件夹下面");
        } else {
            if(file2.createNewFile()) {
                System.out.println("hello.txt文件创建成功");
                FileWriter fileWriter = new FileWriter(file2);
                fileWriter.write("hello World~");
                fileWriter.close();
                System.out.println("写入文件成功");
            }
            else
                System.out.println("hello.txt文件创建失败");
        }

    }
}
