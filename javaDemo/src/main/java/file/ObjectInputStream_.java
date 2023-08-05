package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStream_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fiPath = "./data.dat";
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fiPath));
        System.out.println(objectInputStream.readInt());
        System.out.println(objectInputStream.readBoolean());
        System.out.println(objectInputStream.readChar());
        System.out.println(objectInputStream.readDouble());
        System.out.println(objectInputStream.readUTF());
        Object dog = objectInputStream.readObject();
        System.out.println(dog.getClass());
        System.out.println(dog);
    }
}
