package file;

import java.io.*;

public class ObjectOutputStream_ {
    public static void main(String[] args) throws IOException {
        String ofPath = "./data.dat";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ofPath));
        objectOutputStream.writeInt(100);
        objectOutputStream.writeBoolean(true);
        objectOutputStream.writeChar('n');
        objectOutputStream.writeDouble(9.5);
        objectOutputStream.writeUTF("这里使用UTF序列化字符串");
        objectOutputStream.writeObject(new Dog("wangcai", 15));
        objectOutputStream.close();
        System.out.println("数据序列化保存成功");
    }
}
