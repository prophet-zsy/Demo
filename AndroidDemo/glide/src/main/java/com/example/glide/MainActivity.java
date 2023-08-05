package com.example.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://tse1-mm.cn.bing.net/th/id/R-C.67f066d008b5a4e0acf5beb6cd3546bd?rik=Rn7qehGnNsXohQ&riu=http%3a%2f%2fwww.114mx.com%2fUploads%2fimage%2f20200207%2f20200207110115_60396.jpg&ehk=BOiX8f%2b1BKtPkkGPimnosJv5iJpket7SogBiEhCYnr0%3d&risl=&pid=ImgRaw&r=0";
        imageView = findViewById(R.id.image_view);
        Glide.with(this)
                .load(url)
                .into(imageView);
    }
}
