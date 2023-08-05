package com.example.testt;

import android.media.MediaExtractor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    private String[] data = {"shanghai", "beijing", "guangdong", "shenzhen"};
    private ListView listView;

    private List<Map<String, String >> data2;
    private ListView listView2;
    private Handler handler;
    private static final int MSG_FINISH = 0;
    private static final int MSG_EXCEPTION = 1;

    private ListView listView3;
    private int[] imgs;
    private String[] texts;

    private Button buttonAdd;
    private EditText editText;
    private GridView gridView4;
    private List<String> text2;

    private int count = 1;

    private List<String> grades;
    private Map<String, List<String>> stu;
    private Spinner spinner1, spinner2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MSG_FINISH:
                        SimpleAdapter adapter2 = new SimpleAdapter(
                                Main2Activity.this,
                                data2,
                                R.layout.list_item2,
                                new String[]{"name", "age"},
                                new int[] {R.id.name, R.id.age}
                        );
                        listView2.setAdapter(adapter2);
                        break;
                    case MSG_EXCEPTION:
                        Toast.makeText(Main2Activity.this, "解析数据出错", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        initControl();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Main2Activity.this, "您所选择的城市是：" + data[i], Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                data);
        listView.setAdapter(adapter);

        listView3.setAdapter(new MyAdapter());

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this,
                R.layout.list_item,
                text2
        );

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.add(editText.getText().toString());
                adapter1.notifyDataSetChanged();
            }
        });

        gridView4.setAdapter(adapter1);

        final List<String> spinner2data = new ArrayList<String>();
        spinner1.setAdapter(new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, grades));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinner2data);
        spinner2.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner2data.clear();
                spinner2data.addAll(stu.get(grades.get(position)));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
//            return texts.length;
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(Main2Activity.this);
                view = layoutInflater.inflate(R.layout.list_item3, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
                count ++;
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            ImageView imageView = viewHolder.getImage();
            imageView.setImageResource(imgs[position%texts.length]);
            TextView textView = viewHolder.getTv();
            textView.setText(texts[position%texts.length] + ", count = " + count);
            return view;
        }
    }

    class ViewHolder {
        private View view;
        private ImageView image;
        private TextView tv;
        public ViewHolder(View view) {
            this.view = view;
        }
        public ImageView getImage(){
            if (image == null) {
                image = view.findViewById(R.id.icon);
            }
            return image;
        }
        public TextView getTv() {
            if (tv == null) {
                tv = view.findViewById(R.id.name);
            }
            return tv;
        }
    }


    private void initControl() {
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);
        listView3 = findViewById(R.id.listView3);
//        fillData();
//        解析xml中的数据放到线程中进行
        new Thread() {
            @Override
            public void run() {
                try {
                    fillData2();
                    handler.sendEmptyMessage(MSG_FINISH);
                } catch (IOException e) {
                    handler.sendEmptyMessage(MSG_EXCEPTION);
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    handler.sendEmptyMessage(MSG_EXCEPTION);
                    e.printStackTrace();
                }
            }
        }.start();

        imgs = new int[] {
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground,
        };
        texts = new String[] {
                "android",
                "android",
                "android",
                "android"
        };
        buttonAdd = findViewById(R.id.addElem);
        editText = findViewById(R.id.input);
        gridView4 = findViewById(R.id.gridView4);
        text2 = new ArrayList<String>() {
            {add("android");}
            {add("android");}
            {add("android");}
            {add("android");}
        };
        fillStu();
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
    }

    void fillStu() {
        grades = new ArrayList<>();
        grades.add("Android");
        grades.add("Arm");
        List<String> androidGrd = new ArrayList<>();
        List<String> armGrd = new ArrayList<>();
        androidGrd.add("zhangsan");
        androidGrd.add("lisi");
        armGrd.add("wangwu");
        armGrd.add("zhaoliu");
        stu = new HashMap<>();
        stu.put("Android", androidGrd);
        stu.put("Arm", armGrd);
    }

    void  fillData2 () throws IOException, XmlPullParserException {
//        通过解析xml填充数据
        List<Student> stuList = new ArrayList<>();
        XmlPullParser xmlPullParser = getResources().getXml(R.xml.students);
        int eventType = 0;
        eventType = xmlPullParser.getEventType();
        Student student = null;
        while (eventType!= XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    String name = xmlPullParser.getName();
                    if (name.equals("student")) {
                        student = new Student();
                    } else if (name.equals("name")) {
                        student.setName(xmlPullParser.nextText());
                    } else if (name.equals("age")) {
                        student.setAge(xmlPullParser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = xmlPullParser.getName();
                    if (name.equals("student")) {
                        stuList.add(student);
                    }
                    break;
            }
            eventType = xmlPullParser.next();
        }

//        将解析结果填入data2
        data2 = new ArrayList<>();
        for (Student stu: stuList) {
            Map<String, String> item = new HashMap<>();
            item.put("name", stu.getName());
            item.put("age", stu.getAge());
            data2.add(item);
        }
    }

    class Student {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    void fillData() {
        data2 = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("name", "zhangsan");
        item.put("age", "16");
        data2.add(item);
        Map<String, String> item2 = new HashMap<>();
        item2.put("name", "lisi");
        item2.put("age", "26");
        data2.add(item2);
    }

}
