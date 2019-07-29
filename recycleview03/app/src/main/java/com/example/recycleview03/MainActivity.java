package com.example.recycleview03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.recycleview03.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);

        List<PostItem> items = new ArrayList<>();
        for (int i = 0; i < 15 ; i++) {
            PostItem item = new PostItem().builder()
                    .isLikeUser(true)
                    .likeCount(12)
                    .userName("yoonjh238")
                    .postText("안녕하세요")
                    .imgUrl("이미지 유알엘")
                    .build();

        items.add(item);
        }


    }
}
