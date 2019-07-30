package com.example.yoonstagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.yoonstagram.model.PostItem;
import com.example.yoonstagram.viewHolder.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recycle_view);

        List<PostItem> items = new ArrayList<>();

        for (int i = 0; i < 15; i++) {

            String url = "";
            if (i % 2 == 0)
                url = "http://file3.instiz.net/data/file3/2019/07/21/d/e/1/de13e93a7015cbc04d6c73635c5dea6e.jpg";
            else
                url = url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";
            PostItem item = new PostItem().builder()
                    .userName("yoonjh238")
                    .postText("안녕하세요")
                    .imgUrl(url)
                    .isLike(true)
                    .likeCount(i).build();

            Log.d("IMAGEURL", "onCreate: " + url);
            items.add(item);
        }

        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rv.setAdapter(new PostAdapter(this,items));

    }
}
