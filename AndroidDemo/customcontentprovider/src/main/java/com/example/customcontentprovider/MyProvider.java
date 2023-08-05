package com.example.customcontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.HeaderViewListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        Log.i("TEST", "onCreate");
        dbHelper = new DBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.i("TEST", "query");
        return dbHelper.getReadableDatabase().query("student", projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.i("TEST", "insert");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        long rowId = sqLiteDatabase.insert("student", null, values);
        return ContentUris.withAppendedId(uri, rowId);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.i("TEST", "delete");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.i("TEST", "update");
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.i("TEST", "getType");
        return null;
    }
}
