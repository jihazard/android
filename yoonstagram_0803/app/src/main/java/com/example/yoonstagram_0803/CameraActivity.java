package com.example.yoonstagram_0803;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageView imageview = findViewById(R.id.iv_takeImg);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        Log.d("uri", "onCreate: " + uri);
        Glide.with(this)
                .load(uri)
                .into(imageview);



    }
}
