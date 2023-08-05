package com.example.mvplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvplogin.model.UserBean;
import com.example.mvplogin.presenter.IPresenter;
import com.example.mvplogin.presenter.MainPresenter;

/**
 * MVP中，尽可能将业务逻辑从activity、fragment中分离出来，交给P层，P和V之间通过接口交互，完成解耦，方便替换和复用
 * 但这样引入了较多的接口，小项目中反而没有必要，业务逻辑比较复杂的大项目中MVP比较实用
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private EditText etUser;
    private EditText etPassword;
    private Button btnLogin;
    private Dialog progressDialog;

    private IPresenter iPresenter;

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
//      持有P层接口实例
        iPresenter = new MainPresenter(this);
    }

    @Override
    public void onClick(View v) {
        iPresenter.login(etUser.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void toastForSuccess(UserBean userBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastForFailure(Throwable throwable) {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }
}
