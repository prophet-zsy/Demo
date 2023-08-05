package com.example.a061_computeprimenum;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/**
 *  质数的计算放到子线程中运行，防止计算耗时过长，导致阻塞主线程
 *
 * 【Android在子线程中操作UI：弹出Toast、改变TextView内容】https://blog.csdn.net/cpcpcp123/article/details/104122690
 */

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button cal;

    private CalThread calThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        cal = findViewById(R.id.cal);

        calThread = new CalThread();
        calThread.start();

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = Integer.parseInt(input.getText().toString());
                calThread.handler.sendMessage(message);
            }
        });
    }

//    计算素数的工作线程
    class CalThread extends Thread {
        Handler handler;
        @Override
        public void run() {
//            Toast.makeText(MainActivity.this, "res", Toast.LENGTH_SHORT).show();  // 子线程中在Looper.prepare()之后是可以弹Toast的
            Looper.prepare();
            handler = new Handler(Looper.myLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    int upper = message.what;
                    List<Integer> res = new ArrayList<>();
                    for (int i = 2; i <= upper; i++) {
                        boolean isPrime = true;
                        for (int j = 2; j <= Math.sqrt(i); j++) {
                            if (i % j == 0) isPrime = false;
                        }
                        if (isPrime) res.add(i);
                    }
//                    通过toast显示结果
                    Toast.makeText(MainActivity.this, res + "", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            Looper.loop();
        }
    }
}
