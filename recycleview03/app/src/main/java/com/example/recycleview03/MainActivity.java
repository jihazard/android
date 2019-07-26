package com.example.recycleview03;

import androidx.appcompat.app.AppCompatActivity;

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

        LinearLayout linearLayout = findViewById(R.id.ll_scroll);

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

        for(PostItem item : items){
            View v = View.inflate(this, R.layout.post_item, null);
            TextView postText = v.findViewById(R.id.tv_posttext);
            TextView userName = v.findViewById(R.id.tv_username);

            postText.setText(postText.getText());
            userName.setText(userName.getText());

            linearLayout.addView(v);
        }
    }
}
