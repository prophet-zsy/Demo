package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    private EditText stuName;
    private EditText stuAge;
    private EditText stuScore;
    private ListView listView;

    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase database;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stuName = findViewById(R.id.stuName);
        stuAge = findViewById(R.id.stuAge);
        stuScore = findViewById(R.id.stuScore);
        listView = findViewById(R.id.list_view);
        //        创建数据库，如果数据库不存在的话，存在的话，检查版本，如果版本高于现有版本，进行升级
        myOpenHelper = new MyOpenHelper(this, "test.db");
        database = myOpenHelper.getReadableDatabase();

        showAllStu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
        myOpenHelper.close();
    }

    public void onButton(final View view) {
        switch (view.getId()) {
            case R.id.addStu:
                String name = stuName.getText().toString();
                String age = stuAge.getText().toString();
                String score = stuScore.getText().toString();
                database.execSQL("insert into test values(null, '" + name + "', " + age + ", " + score + ")");
                showAllStu();
                Toast.makeText(this, "数据插入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.queryStu:
                final View dlgView = LayoutInflater.from(this).inflate(R.layout.dlg_view, null);
                final EditText inputId = dlgView.findViewById(R.id.input_id);
                new AlertDialog.Builder(this)
                        .setView(dlgView)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = inputId.getText().toString();
                                Cursor cur = database.query(
                                        "test",
                                        null,
                                        "name = ?",
                                        new String[] {name},
                                        null,
                                        null,
                                        null
                                );
                                if (cur.moveToNext()) {
                                    display(cur);
                                }
                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.deleteStu:
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                database.delete("test", "_id = ?", new String[]{String.valueOf(_id)});
                stuName.setText("");
                stuAge.setText("");
                stuScore.setText("");
                showAllStu();
                break;
            case R.id.updateStu:
                ContentValues contentValues = new ContentValues();
                contentValues.put("age", Integer.valueOf(stuAge.getText().toString()));
                contentValues.put("score", Double.valueOf(stuScore.getText().toString()));
                database.update("test", contentValues, "name = ?", new String[]{stuName.getText().toString()});
                showAllStu();
                break;
        }
    }

    public void browser (View view) {
        switch (view.getId()) {
            case R.id.firstOne:
                if(cursor.moveToFirst()) {
                    display();
                }
                break;
            case R.id.llastOne:
                if (cursor.moveToPrevious()) {
                    display();
                }
                break;
            case R.id.nextOne:
                if (cursor.moveToNext()) {
                    display();
                }
                break;
            case R.id.lastOne:
                if (cursor.moveToLast()) {
                    display();
                }
                break;
        }
    }

    private void showAllStu() {
        cursor = database.query(
                "test",
                null,
                null,  // "name = ?"
                null, // new String[]{"zhang"}
                null,
                null,
                null
        );
//                适配器方案1
//                CursorAdapter adapter = new CursorAdapter(this, cursor) {
//                    @Override
//                    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.student_item, null);
//                        return view;
//                    }
//
//                    @Override
//                    public void bindView(View view, Context context, Cursor cursor) {
//                        TextView tvName = view.findViewById(R.id.tvName);
//                        TextView tvAge = view.findViewById(R.id.tvAge);
//                        TextView tvScore = view.findViewById(R.id.tvScore);
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//                        int age = cursor.getInt(cursor.getColumnIndex("age"));
//                        double score = cursor.getDouble(cursor.getColumnIndex("score"));
//                        tvName.setText(name);
//                        tvAge.setText(String.valueOf(age));
//                        tvScore.setText(String.valueOf(score));
//                    }
//                };
//                适配器方案2
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.student_item, cursor,
                new String[]{"name", "age", "score"},
                new int[]{R.id.tvName, R.id.tvAge, R.id.tvScore}
        );
        listView.setAdapter(adapter);
    }


    private void display() {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int age = cursor.getInt(cursor.getColumnIndex("age"));
        double score = cursor.getDouble(cursor.getColumnIndex("score"));
        stuName.setText(name);
        stuAge.setText(String.valueOf(age));
        stuScore.setText(String.valueOf(score));
    }
    private void display(Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int age = cursor.getInt(cursor.getColumnIndex("age"));
        double score = cursor.getDouble(cursor.getColumnIndex("score"));
        stuName.setText(name);
        stuAge.setText(String.valueOf(age));
        stuScore.setText(String.valueOf(score));
    }

}
