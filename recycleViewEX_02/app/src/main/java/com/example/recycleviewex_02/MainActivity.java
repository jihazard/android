package com.example.recycleviewex_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recycleviewex_02.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PostItem> items = new ArrayList<>();
        LinearLayout linearLayout = findViewById(R.id.ll_scroll);

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

        for (PostItem item : items){
            View v = View.inflate(this, R.layout.post_item, null);

            TextView tvUserName = v.findViewById(R.id.tv_username);
            TextView tvPostText = v.findViewById(R.id.tv_posttext);

            tvUserName.setText(tvUserName.getText());
            tvPostText.setText(tvPostText.getText());


            linearLayout.addView(v);
        }



    }
}
