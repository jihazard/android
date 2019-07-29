package com.example.glidetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv;
    private Button  btn , btn2,btn3;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv_imgView);
        btn = findViewById(R.id.btn_btn);
        btn.setOnClickListener(this);
        btn2 = findViewById(R.id.btn_btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn_btn3);
        btn3.setOnClickListener(this);


    }



    private void startLodingImage(int id) {
        String url = "";
        if(id == R.id.btn_btn) url = "http://file3.instiz.net/data/file3/2019/07/21/d/e/1/de13e93a7015cbc04d6c73635c5dea6e.jpg";
        else if(id == R.id.btn_btn2) url = "https://cdnimg.melon.co.kr/cm/artistcrop/images/002/61/143/261143_500.jpg?32b7688ac5eb168fa11891d572f7b23d/melon/resize/416/quality/80/optimize";
        else if(id == R.id.btn_btn3) url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";

        Glide.with(this).load(url).into(iv);

        //iv.setImageBitmap();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_btn:
                startLodingImage(R.id.btn_btn);
                break;

            case R.id.btn_btn2:
                startLodingImage(R.id.btn_btn2);
                break;

            case R.id.btn_btn3:
                startLodingImage(R.id.btn_btn3);
                break;
        }
    }
}
