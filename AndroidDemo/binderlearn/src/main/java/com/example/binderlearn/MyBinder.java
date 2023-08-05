package com.example.binderlearn;

public class MyBinder {

    static {
        System.loadLibrary("native-lib");
    }

    public native void write(String str);
    public native String read();
}
