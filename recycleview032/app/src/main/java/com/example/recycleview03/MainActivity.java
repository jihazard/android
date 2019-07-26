package com.example.recycleview03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recycleview03.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<PostItem> items = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv_list);


        for (int i = 0; i < 11 ; i++) {
            PostItem item = new PostItem().builder()
                    .isLike(true)
                    .userName("윤지환")
                    .postText("안녕하세요")
                    .imgUrl("ㅇㄹㄴㅇㄹ")
                    .likeCount(19)
                    .build();
            items.add(item);
        }

    }
}
