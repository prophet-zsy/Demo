package com.example.syscontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<User> userList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryAndShow();

//        test();
    }

    private void queryAndShow() {
        userList = new ArrayList<>();
//        getAllContacts();
        getAllContacts2();
        for (User ite : userList) Log.i("TEST", String.valueOf(ite));
        listView = findViewById(R.id.list_view);

        List<Map<String, String>> data = new ArrayList<>();
        for (User ite : userList) {
            Map<String , String> map = new HashMap<>();
            map.put("name", ite.getName());
            map.put("phone", ite.getPhone());
            data.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[] {"name", "phone"},
                new int[] {R.id.text1, R.id.text2}
        );

        listView.setAdapter(simpleAdapter);
    }

    /**
     * 使用新版本公开的uri完成查询
     */
    private void getAllContacts2() {
        Uri uri = ContactsContract.Data.CONTENT_URI;
//        所有的数据一般存在data1
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        int index_contact_id = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID);
        int index_data1 = cursor.getColumnIndex(ContactsContract.Data.DATA1);
        int index_mimetype = cursor.getColumnIndex(ContactsContract.Data.MIMETYPE);

//        这里的结构是这样，
//        1 1 (561) 513-6816    vnd.android.cursor.item/phone_v2
//        1 zhangsan            vnd.android.cursor.item/name
//        2 2345674563324       vnd.android.cursor.item/phone_v2
//        2 lisi                vnd.android.cursor.item/name
//        因此需要下面的处理逻辑

        int tempId = -1;
        User user = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(index_contact_id);
            String data1 = cursor.getString(index_data1);
            String mimetype = cursor.getString(index_mimetype);

            if (tempId != id) {
                if (tempId != -1)
                    userList.add(user);
                user = new User();
                user.set_id(id);
                tempId = id;
            }
            if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                user.setPhone(data1);
            }
            if (mimetype.equals("vnd.android.cursor.item/name")) {
                user.setName(data1);
            }
        }
        userList.add(user);
    }

    /**
     * 使用老版本公开的uri完成查询
     */
    private void getAllContacts() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;//联系人名称 表
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;//电话号码 表
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            int _id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            user.set_id(_id);
            user.setName(name);

            Cursor curPhone = contentResolver.query(phoneUri, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{String.valueOf(_id)}, null);
            while (curPhone.moveToNext()) {
                String number = curPhone.getString(curPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                user.setPhone(number);
            }
            userList.add(user);
        }
    }


    private void test() {
        /*
        总的就是获取ContentResolver，然后去共享的数据库中查询联系人相关的信息
         */

//        ContentResolver contentResolver = getContentResolver();
//        Uri uri = ContactsContract.Contacts.CONTENT_URI;//联系人名称
//        Cursor cursor = contentResolver.query(uri, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, null, null );
//        while (cursor.moveToNext()) {
//            Log.i("TEST", cursor.getString(0));
//        }

//        ContentResolver contentResolver = getContentResolver();
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;//电话号码
//        Cursor cursor = contentResolver.query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null );
//        while (cursor.moveToNext()) {
//            Log.i("TEST", cursor.getString(0));
//        }

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.Data.CONTENT_URI;//2.X以上版本时可用
        Cursor cursor = contentResolver.query(uri, new String[]{ContactsContract.Data.DATA1}, null, null);
        while (cursor.moveToNext()) {
            Log.i("TEST", cursor.getString(0));
        }
    }

}
