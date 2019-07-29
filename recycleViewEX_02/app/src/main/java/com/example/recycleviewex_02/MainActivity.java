package com.example.recycleviewex_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recycleviewex_02.model.PostItem;
import com.example.recycleviewex_02.recycleView.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PostItem> items = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycleView);

        for (int i = 0; i < 15 ; i++) {
            PostItem list = new PostItem().builder()
                    .isUserLike(true)
                    .postImgUrl("imgurl")
                    .postLikeCount(i)
                    .postText("post text " + i)
                    .username("yoonjh23" + i)
                    .build();
            ;

            items.add(list);
        }

        PostAdapter postAdapter = new PostAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView .VERTICAL,false));
        recyclerView.setAdapter(postAdapter);

    }
}
