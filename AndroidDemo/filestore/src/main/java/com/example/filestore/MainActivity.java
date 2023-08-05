package com.example.filestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ops (@NonNull View view) {
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        switch (view.getId()) {
            case R.id.write:
                try {
                    FileOutputStream fileOutputStream = openFileOutput("test.txt", MODE_PRIVATE);
//                    这里不能使用java中的文件字节流，因为安卓需要在data/data文件夹下进行文件操作，需要使用安卓中给定的openFileOutput以对应到指定路径下
//                    FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    bufferedWriter.write(((EditText)findViewById(R.id.textContent)).getText().toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.read:
                try {
                    FileInputStream fileInputStream = openFileInput("test.txt");
//                    这里不能使用java中的文件字节流，因为安卓需要在data/data文件夹下进行文件操作，需要使用安卓中给定的openFileInput以对应到指定路径下
//                    FileInputStream fileInputStream = new FileInputStream("test.txt");
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    String line = bufferedReader.readLine();
                    ((EditText)findViewById(R.id.textContent)).setText(line);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.readRaw:
                try {
                    InputStream inputStream = getResources().openRawResource(R.raw.test);
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = bufferedReader.readLine();
                    ((EditText)findViewById(R.id.textContent)).setText(line);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.writeSD:
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
//                    Toast.makeText(this, "SD卡处于挂载状态",Toast.LENGTH_SHORT).show();
//                    File file = new File(Environment.getStorageDirectory(), "test.txt");  // 在API29不推荐使用
                    File file = new File(this.getExternalFilesDir(null).getPath(), "test.txt");
                    try {
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                        bufferedWriter.write(((EditText)findViewById(R.id.textContent)).getText().toString());
                        Toast.makeText(this, "写入SD卡成功",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.readSD:
                state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
//                    Toast.makeText(this, "SD卡处于挂载状态",Toast.LENGTH_SHORT).show();
//                    File file = new File(Environment.getStorageDirectory(), "test.txt");    // 在API29不推荐使用
                    File file = new File(this.getExternalFilesDir(null).getPath(), "test.txt");
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        String line = bufferedReader.readLine();
                        ((EditText)findViewById(R.id.textContent)).setText(line);
                        Toast.makeText(this, "读取SD卡成功",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }
    }
}
