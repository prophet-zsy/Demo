package com.example.mvclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvclogin.model.Callback;
import com.example.mvclogin.model.LoginModel;
import com.example.mvclogin.model.UserBean;

/**
 * activity、fragment中既包含了V层的功能，又包含了C层的功能，并且直接持有M层，和M层交互
 * V层包括activity、fragment、xml布局文件，C层指业务逻辑控制的部分
 * M 数据逻辑
 * V 视图逻辑
 * C 业务逻辑
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUser;
    private EditText etPassword;
    private Button btnLogin;
    private LoginModel loginModel;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      ui初始化
        etUser = findViewById(R.id.user);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
//      ui设置
        btnLogin.setOnClickListener(this);
        progressDialog = new AlertDialog.Builder(this)
                .setView(new ProgressBar(this))
                .create();
//      持有M层实例
        loginModel = new LoginModel();
    }

    @Override
    public void onClick(View v) {
        progressDialog.show();
        loginModel.login(etUser.getText().toString(), etPassword.getText().toString(), new Callback() {
            @Override
            public void onSuccess(UserBean userBean) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable throwable) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
