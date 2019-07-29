package com.example.ex0729_viewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ex0729_viewer.adapter.Adapter;
import com.example.ex0729_viewer.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            RecyclerView rv = findViewById(R.id.view_recycler);
            List<PostItem> items = new ArrayList<>();

            for (int i = 0; i < 15; i++) {
                PostItem item = new PostItem().builder()
                        .userName("yoonjh238")
                        .postText("안녕하세요")
                        .imgUrl("www.naver.com")
                        .isLike(true)
                        .likeCount(i).build();

                items.add(item);
            }
            rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            rv.setAdapter(new Adapter(this,items));


            for (PostItem item : items){
                System.out.println(item.toString());
            }
    }

}
