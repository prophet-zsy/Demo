package com.example.testt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private CheckBox me, love, lianyali;
    private TextView tv;
    private Button work, showIdx;
    private Button xmlParser, showXML;
    private Handler handler1;
    private View view;
    private Dialog dialog;

    private Button transActivity2;
    private Button transSysActivity;
    private Button StartActivityResult;
    private Button transMainActivity;
    private TextView activity_id;
    private Button SingTopActivity;
    private Button SingTaskActivity;
    private Button SingInsActivity;
    private Button SingInsActivity2;

    private FragmentManager fragmentManager;
    private MyFragment myFragment;
    private MyFragment myFragment2;
    private Button changeFrag;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        initControl();

    }

    void initControl() {
        me = findViewById(R.id.me);
        love = findViewById(R.id.love);
        lianyali = findViewById(R.id.lianyali);
        tv = findViewById(R.id.tv);
        work = findViewById(R.id.syncWork);
        showIdx = findViewById(R.id.showIdx);
        xmlParser = findViewById(R.id.XMLpaser);
        showXML = findViewById(R.id.showXML);
        transActivity2 = findViewById(R.id.transActivity2);
        transSysActivity = findViewById(R.id.transSysActivity);
        StartActivityResult = findViewById(R.id.StartActivityForResult);
        transMainActivity = findViewById(R.id.transMainActivity);
        activity_id = findViewById(R.id.activity_id);
        SingTopActivity = findViewById(R.id.SingTopActivity);
        SingTaskActivity = findViewById(R.id.SingTaskActivity);
        SingInsActivity = findViewById(R.id.SingInsActivity);
        SingInsActivity2 = findViewById(R.id.SingInsActivity2);
        changeFrag = findViewById(R.id.changeFrag);

        CheckedChangeListener listener = new CheckedChangeListener();
        me.setOnCheckedChangeListener(listener);
        love.setOnCheckedChangeListener(listener);
        lianyali.setOnCheckedChangeListener(listener);
        createCustomDialog();

        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                int idx = msg.arg1;
                showIdx.setText(idx+"");
                return false;
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Message msg = new Message();
                            msg.arg1 = i;
                            handler.sendMessage(msg);
                        }
                    }
                }.start();
            }
        });

        handler1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                showXML.setText((String) msg.obj);
                return false;
            }
        });

        transActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("second");
                intent.addCategory("android.intent.category.test");
                startActivity(intent);

            }
        });

        transSysActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("content://contacts/people/"));
                startActivity(intent);
            }
        });

        StartActivityResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                intent.putExtra("num1", 10);
                intent.putExtra("num2", 20);
                startActivityForResult(intent, 0x001);
            }
        });

        transMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        activity_id.setText(this.toString() + "activity_info:" + getTaskId());

        SingTopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SingTopActivity.class);
                startActivity(intent);
            }
        });

        SingTaskActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SingleTaskActivity.class);
                startActivity(intent);
            }
        });

        SingInsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SingleInsActivity.class);
                startActivity(intent);
            }
        });
        SingInsActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SingleInsActivity2.class);
                startActivity(intent);
            }
        });

        fragmentManager = getSupportFragmentManager();
        myFragment = (MyFragment) fragmentManager.findFragmentById(R.id.frag1);
        myFragment2 = (MyFragment) fragmentManager.findFragmentByTag("frag_tag");
        changeFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFragment2.setGreeting("你好，世界！");
            }
        });

    }


    public void drawerLayout(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DrawerLayoutActivity.class);
        startActivity(intent);
    }

    public void viewPager(View view) {
        Class cls = ViewPagerActivity.class;
        switch (view.getId()) {
            case R.id.viewPager:
                cls = ViewPagerActivity.class;
                break;
            case R.id.viewPager2:
                cls = FixedTabActivity.class;
                break;
        }
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, cls);
        startActivity(intent);
    }

    public void showDialogOrFragment (View view) {
        switch (view.getId()) {
            case R.id.showDialog:
                MyDialog.newInstance("this is title").show(getSupportFragmentManager(), "DIALOG");
                break;
            case R.id.showListDialog:
                MyListFragment myListFragment = MyListFragment.newInstance(new int[]{0,1,2,3});
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.layout2, myListFragment, "LIST_FRAGMENT")
                        .commit();
                break;
        }
    }

    public static class MyListFragment extends ListFragment {
        public static MyListFragment newInstance(int[] data) {
            Bundle args = new Bundle();
            args.putIntArray("data", data);
            MyListFragment fragment = new MyListFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            int[] data = getArguments().getIntArray("data");
            List<Integer> newData = new ArrayList<>();
            for (int i = 0; i < data.length; i ++) newData.add(data[i]);
            setListAdapter(new ArrayAdapter<>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    newData
            ));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Toast.makeText(getActivity(), "您点击了" + getArguments().getIntArray("data")[position], Toast.LENGTH_SHORT).show();
            super.onListItemClick(l, v, position, id);
        }
    }

    public static class MyDialog extends DialogFragment {
        public static MyDialog newInstance(String title) {
            Bundle args = new Bundle();
            args.putString("title", title);
            MyDialog fragment = new MyDialog();
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("title"))
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setMessage("blablablabla")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", null)
                    .show();
        }
    }

    public void operateFrag(View view) {
        switch (view.getId()) {
            case R.id.addFrag:
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(R.id.layout, new MyFragment(), "TEST_TAG");
                ft.commit();
                break;
            case R.id.removeFrag:
                ft = fragmentManager.beginTransaction();
                Fragment fragment = fragmentManager.findFragmentByTag("TEST_TAG");
                if (null != fragment)
                    ft.remove(fragment);
                ft.commit();
                break;
            case R.id.hideOrShowFrag:
                ft = fragmentManager.beginTransaction();
                fragment = fragmentManager.findFragmentByTag("TEST_TAG");
                if (null != fragment) {
                    if (fragment.isHidden()) {
                        ft.show(fragment);
                    } else {
                        ft.hide(fragment);
                    }
                }
                ft.commit();
                break;
            case R.id.replaceFrag:
                ft = fragmentManager.beginTransaction();
                ft.replace(R.id.layout, MyFragment.newInstance(num++));
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }

    public void matchPhoneTablet (View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, PhoneTabletActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0x001 && resultCode == 0x002) {
            int res = data.getIntExtra("result", -1);
            Log.i("TEST", String.valueOf(res));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createCustomDialog () {
        dialog = new Dialog(this);
        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        final Button confirm = view.findViewById(R.id.buttonOk);
        final EditText user = view.findViewById(R.id.editText2);
        final EditText pssword = view.findViewById(R.id.editText3);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您保存了：" + user.getText() + pssword.getText(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
    }

    public void displayCustomDialog (View view) {
        dialog.show();
    }

    public void displayProgressDialog (View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("进度条对话框");
        progressDialog.setMessage("这是一个进度条对话框");
        progressDialog.setIcon(R.drawable.ic_launcher_foreground);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                progressDialog.setProgress(msg.arg1);
                return false;
            }
        });
        progressDialog.show();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i ++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.arg1 = i;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    public void displayListDialog (View view) {
        final String[] items = {"zhang", "liu", "lian", "chen"};
        switch (view.getId()) {
            case R.id.button12:
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("列表对话框")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您点击了" + items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.button14:
                final AtomicInteger choseId = new AtomicInteger();
                choseId.set(-1);
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("单选对话框")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                choseId.set(which);
                                Toast.makeText(MainActivity.this, "您点击了" + items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您选择了" + items[choseId.get()], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.button21:
                final boolean[] defult = new boolean[items.length];
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("多选对话框")
                        .setMultiChoiceItems(items, defult, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                String check;
                                if (isChecked) check = "选择";
                                else check = "取消";
                                Toast.makeText(MainActivity.this, "您"+check+"了" + items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String res = "";
                                for (int i = 0; i < items.length; i ++) if (defult[i]) res += (items[i] + ",");
                                Toast.makeText(MainActivity.this, "您选择了" + res, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }
    }

    public void displayDialog (View view) {
        switch (view.getId()) {
            case R.id.button15:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Test")
                        .setMessage("这是一个测试对话框")
                        .create();
                alertDialog.show();
                break;
            case R.id.button23:
                alertDialog = new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setTitle("Test")
                        .setMessage("这是一个测试对话框")
                        .setNegativeButton("确定",new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您点击了确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("忽略", null)
                        .setPositiveButton("取消", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "您点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
                break;
        }
    }

    public void displayToast(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button16:
                Toast.makeText(this, "这是一个toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button17:
                Toast toast = new Toast(this);
                TextView tv = new TextView(this);
                tv.setText("这是一个通过构造显示的toast");
                toast.setView(tv);
                toast.setGravity(Gravity.CENTER, 100, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.button18:
                toast = new Toast(this);
                View v = getLayoutInflater().inflate(R.layout.toast_view, null);
                toast.setView(v);
                toast.setGravity(Gravity.CENTER, 100, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }

    public void jsonParser (View view) throws JSONException {
        String data = "{\"name\": \"Tom\",\"names\":[{\"1\":\"Zhang\"},{\"2\":\"Li\"}]}";
        StringBuffer sb = new StringBuffer();
        JSONObject jsonObject = new JSONObject(data);
        String name = jsonObject.getString("name");
        sb.append(name + "\n");
        JSONArray names = jsonObject.getJSONArray("names");
        for (int i = 0; i < names.length(); i ++) {
            JSONObject jsonObject1 = names.getJSONObject(i);
            String nameItem = jsonObject1.getString(String.valueOf(i+1));
            sb.append(nameItem + "\n");
        }
        Log.i("TEST", sb.toString());
        Message message2 = Message.obtain();
        message2.obj = sb.toString();
        handler1.sendMessage(message2);

//        new Thread() {
//            @Override
//            public void run() {
//                StringBuffer str = new StringBuffer();
//                XmlPullParser parser = getResources().getXml(R.xml.test);
//                int eventType = 0;
//                try {
//                    eventType = parser.getEventType();
//                } catch (XmlPullParserException e) {
//                    e.printStackTrace();
//                }
//                while (eventType != XmlPullParser.END_DOCUMENT) {
//                    switch (eventType) {
//                        case XmlPullParser.START_DOCUMENT:
//                            Log.i("TEST", "START_DOCUMENT");
//                            str.append("START_DOCUMENT\n");
//                            break;
//                        case XmlPullParser.END_DOCUMENT:
//                            Log.i("TEST", "END_DOCUMENT");
//                            str.append("END_DOCUMENT\n");
//                            break;
//                        case XmlPullParser.START_TAG:
//                            Log.i("TEST", "START_TAG:" + parser.getName());
//                            str.append("START_TAG" + parser.getName() + "\n");
//                            break;
//                        case XmlPullParser.END_TAG:
//                            Log.i("TEST", "END_TAG" + parser.getName());
//                            str.append("END_TAG" + parser.getName()+"\n");
//                            break;
//                    }
//                    try {
//                        eventType = parser.next();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (XmlPullParserException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Message message = Message.obtain();
//                message.obj = str.toString();
//                handler1.sendMessage(message);
//            }
//        }.start();

    }

    class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        String tem1, tem2, tem3;
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.me:
                    if (isChecked)
                        tem1 = "我";
                    else
                        tem1 = "";
                    break;
                case R.id.love:
                    if (isChecked)
                        tem2 = "爱";
                    else
                        tem2 = "";
                    break;
                case R.id.lianyali:
                    if (isChecked)
                        tem3 = "练娅莉";
                    else
                        tem3 = "";
                    break;
            }
            String res = tem1+tem2+tem3;
            tv.setText(res);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
