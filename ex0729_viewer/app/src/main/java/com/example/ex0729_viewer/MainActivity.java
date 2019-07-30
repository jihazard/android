package com.example.ex0729_viewer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.ex0729_viewer.adapter.Adapter;
import com.example.ex0729_viewer.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            RecyclerView rv = findViewById(R.id.view_recycler);
            List<PostItem> items = new ArrayList<>();

            for (int i = 0; i < 15; i++) {

                String url = "";
                if(i % 2 == 0) url = "http://file3.instiz.net/data/file3/2019/07/21/d/e/1/de13e93a7015cbc04d6c73635c5dea6e.jpg";
                else  url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";


                PostItem item = new PostItem().builder()
                        .userName("yoonjh238")
                        .postText("안녕하세요")
                        .imgUrl(url)
                        .isLike(true)
                        .likeCount(i).build();

                items.add(item);
            }
            rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            rv.setAdapter(new Adapter(this,items));

        View btn = findViewById(R.id.fab_post);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle extras = data.getExtras();
            Log.d("onActivityResult" , " camera sucees ss " + resultCode +"//" + requestCode);
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = null;
            imageView.setImageBitmap(imageBitmap);


        }
    }
}
