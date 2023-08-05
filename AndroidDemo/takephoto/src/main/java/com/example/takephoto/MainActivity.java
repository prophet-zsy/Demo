package com.example.takephoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static androidx.core.content.FileProvider.getUriForFile;

public class MainActivity extends AppCompatActivity {

    File file;
    Uri uri;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        sysCamera();
    }

    private void sysCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File path = new File(getFilesDir(), "images");
        path.mkdirs();  // 这里要创建一下不存在的文件夹，否则，不保存也不报错
        file = new File(path, "HEY" + System.currentTimeMillis() + ".jpg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API >= 24,高版本的API不再允许各app之间通过"file://"形式的uri共享文件
            uri = getUriForFile(this, getPackageName() + ".provider", file);
            Log.i("TEST", String.valueOf(uri));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            Log.i("TEST", "Uri.fromFile");
            uri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("TEST", String.valueOf(resultCode));
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Log.i("TEST", "onActivityResult");
            image.setImageURI(uri);
//            或者 uri转换为bitmap，然后显示
//            Bitmap bitmap = null;
//            try {
//                InputStream is = getContentResolver().openInputStream(uri);
//                bitmap = BitmapFactory.decodeStream(is);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            image.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
