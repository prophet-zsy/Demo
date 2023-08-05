package com.ty.aidlstudy.size;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.ty.aidlstudy.NetByte;

import com.ty.aidlstudy.UserMessage;

import java.util.LinkedList;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private String tag = this.getClass().getCanonicalName();

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.content)
    EditText content;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.send)
    TextView send;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.list_view)
    ListView listView;

    private LinkedList<String> list = new LinkedList<>();
    private ArrayAdapter<String> adapter;
    private NetByte netService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Intent intent2 = new Intent().setComponent(new ComponentName(
                "com.ty.aidlstudy.size",
                "com.ty.aidlstudy.size.NetService"));
        bindService(intent2, mConnection2, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection mConnection1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(tag, "size mConnection1 onServiceConnected");
            netService = NetByte.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    private ServiceConnection mConnection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(tag, "size mConnection2 onServiceConnected");

            NetService.InnerIBinder innerIBinder = (NetService.InnerIBinder) service;
            NetService remoteService = (NetService) innerIBinder.getService();
            remoteService.setCallBack(new NetService.CallBack() {
                @Override
                public void showMessage(UserMessage message) {
                    Log.d("size showMessage impl", Thread.currentThread().getName());
                    list.addFirst("client: " + message.messageContent);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(tag, "offServiceConnected");
        }
    };

    @OnClick(R.id.send)
    public void onViewClicked() {
        if ("".equals(content.getText().toString().trim())) {
            Toast.makeText(this, "请输入信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (netService == null) {
            Toast.makeText(this, "服务正在启动...", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent().setComponent(new ComponentName(
                    "com.ty.aidlstudy.demo",
                    "com.ty.aidlstudy.demo.NetService"));

            boolean bindRes = bindService(intent1, mConnection1, Context.BIND_AUTO_CREATE);
            Log.d(tag, "onViewClicked: " + bindRes);
            return;
        }

        try {
            netService.sendMessage(new UserMessage(content.getText().toString().trim()));
            list.addFirst("server: " + content.getText().toString().trim());
            content.setText("");
            adapter.notifyDataSetChanged();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}