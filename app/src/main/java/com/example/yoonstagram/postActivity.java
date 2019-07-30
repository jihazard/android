package com.example.yoonstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class postActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        TextView tv = findViewById(R.id.tv_tete);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        tv.setText(data);



    }
}
