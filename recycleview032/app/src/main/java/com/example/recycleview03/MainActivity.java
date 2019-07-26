package com.example.recycleview03;

import androidx.appcompat.app.AppCompatActivity;

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

        LinearLayout linearLayout = findViewById(R.id.ll_scroll);

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
    int i = 1;
    for(PostItem item : items){
        View v = View.inflate(this, R.layout.post_item, null);
        TextView postText = v.findViewById(R.id.tv_posttext);
        TextView userName = v.findViewById(R.id.tv_username);
        postText.setText(postText.getText() + String.valueOf(i));

        i++;
        linearLayout.addView(v);
    }
    }
}
