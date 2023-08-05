package com.example.customcontentproviderurimatcher;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.HeaderViewListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {

    private DBHelper dbHelper;
    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("MyProvider2", "student", 0);
        uriMatcher.addURI("MyProvider2", "teacher", 1);
    }

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
        int code = uriMatcher.match(uri);
        Cursor cursor = null;
        switch (code) {
            case 0:
                cursor = dbHelper.getReadableDatabase().query("student", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 1:
                cursor = dbHelper.getReadableDatabase().query("teacher", projection, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.i("TEST", "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.i("TEST", "insert");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        int code = uriMatcher.match(uri);
        long rowId = -1;
        switch (code) {
            case 0:
                rowId = sqLiteDatabase.insert("student", null, values);
                break;
            case 1:
                rowId = sqLiteDatabase.insert("teacher", null, values);
                break;
        }
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
}
