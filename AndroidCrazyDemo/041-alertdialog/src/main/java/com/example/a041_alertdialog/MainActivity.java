package com.example.a041_alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * 可以在dialog中装入不同布局的对话框
 *     可以装入msg、纯列表、单选列表、多选列表、直接传入adapter、view 等几种不同的类型
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dialog(View view) {
        switch (view.getId()) {
            case R.id.simpleDialog:
                new AlertDialog.Builder(this)
                        .setTitle("简单对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("对话框的测试内容\n 第二行内容")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case R.id.listDialog:
                String[] items = new String[] {
                        "疯狂java讲义",
                        "疯狂android讲义",
                        "疯狂前端开发讲义",
                };
                new AlertDialog.Builder(this)
                        .setTitle("列表项对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(items, null)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case R.id.singleChooseListDialog:
                String[] singleChooseItems = new String[] {
                        "疯狂java讲义",
                        "疯狂android讲义",
                        "疯狂前端开发讲义",
                };
                new AlertDialog.Builder(this)
                        .setTitle("单选列表项对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setSingleChoiceItems(singleChooseItems, 0, null)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case R.id.multiChooseListDialog:
                String[] multiChooseItems = new String[] {
                        "疯狂java讲义",
                        "疯狂android讲义",
                        "疯狂前端开发讲义",
                };
                new AlertDialog.Builder(this)
                        .setTitle("多选列表项对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(multiChooseItems, new boolean[multiChooseItems.length], null)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case R.id.customListDialog:
                String[] cutsomListItems = new String[] {
                        "疯狂java讲义",
                        "疯狂android讲义",
                        "疯狂前端开发讲义",
                };
                new AlertDialog.Builder(this)
                        .setTitle("自定义列表项对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cutsomListItems), null)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
            case R.id.customViewDialog:
                new AlertDialog.Builder(this)
                        .setTitle("自定义View对话框")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(LayoutInflater.from(this).inflate(R.layout.login, null, false))
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                break;
        }
    }
}
