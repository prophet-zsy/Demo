package JDBC.ManHanLouRestaurant.Utils;

import java.util.Scanner;

public class Utility {

    public static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
