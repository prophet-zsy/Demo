package file;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Propertires3 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("ip", "192.168.10.13");
        properties.setProperty("user", "张");
        properties.setProperty("pwd", "zhang");
//        properties.store(new FileWriter("./test1.txt"), null);
        properties.store(new FileOutputStream("./test1.txt"), null);
        System.out.println("配置文件保存成功");
    }
}
