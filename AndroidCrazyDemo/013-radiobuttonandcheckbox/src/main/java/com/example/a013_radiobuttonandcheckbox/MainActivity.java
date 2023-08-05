package com.example.a013_radiobuttonandcheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup gender;
    private RadioButton man;
    private RadioButton woman;
    private CheckBox red;
    private CheckBox blue;
    private CheckBox green;
    private TextView show;
    private String genderShow = "";
    private String[] colors = new String[] {
            "您喜欢的颜色为：",
            "",
            "",
            ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    private void initView() {
        gender = findViewById(R.id.gender);
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        show = findViewById(R.id.show);
    }

    private void registerListener() {
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.man) genderShow = "您的性别是男人，";
                else genderShow = "您的性别是女人，";
                showResult();
            }
        });
        red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) colors[1] = "红色,";
                else colors[1] = "";
                showResult();
            }
        });
        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) colors[2] = "蓝色,";
                else colors[2] = "";
                showResult();
            }
        });
        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) colors[3] = "绿色,";
                else colors[3] = "";
                showResult();
            }
        });
    }

    private void showResult() {
        show.setText(genderShow + joinStrArr(colors));
    }

    private String joinStrArr(String[] arr) {
        boolean noColor = true;
        for (int i = 1; i < arr.length; i++) {
            if (! arr[i].equals("")) noColor = false;
        }
        if (noColor) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
        }
        return stringBuilder.toString();
    }
}
