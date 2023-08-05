package com.example.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/*
该类的一个实例对应一个数据库
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION = 2;

    public MyOpenHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, VERSION);
    }
    /*
    当数据库文件不存在时，调用并且只调用一次
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TEST", "onCreate");
        db.execSQL("create table test (_id integer primary key, name varchar(32), age int, score double)");
    }

    /*
    比对版本，版本不一致时，进行升级所做的操作，定义在这里
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TEST", "onUpgrade: oldVersion" + oldVersion + ", newVersion " + newVersion);
        db.execSQL("drop table test");
        db.execSQL("create table test (_id int, name varchar(32), content varchar(32))");
    }
}
