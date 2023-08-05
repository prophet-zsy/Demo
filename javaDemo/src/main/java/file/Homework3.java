package file;

import java.io.*;
import java.util.Properties;

public class Homework3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.setProperty("name", "tom");
        properties.setProperty("age", "5");
        properties.setProperty("color", "red");
        properties.store(new FileWriter("./Dog.properties"), null);
        properties.load(new FileReader("./Dog.properties"));
        Dogg dogg = new Dogg(properties.getProperty("name"), Integer.valueOf(properties.getProperty("age")), properties.getProperty("color"));
        System.out.println(dogg);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("./Dog.dat"));
        objectOutputStream.writeObject(dogg);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("./Dog.dat"));
        dogg = (Dogg) objectInputStream.readObject();
        System.out.println(dogg);
        objectInputStream.close();
    }
    static class Dogg implements Serializable {
        String name;
        int age;
        String color;

        public Dogg(String name, int age, String color) {
            this.name = name;
            this.age = age;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
}
