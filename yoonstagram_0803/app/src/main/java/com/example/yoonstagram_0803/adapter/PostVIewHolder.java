package com.example.yoonstagram_0803.adapter;

import android.graphics.drawable.DrawableContainer;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoonstagram_0803.R;

import java.io.IOException;

import okhttp3.internal.http2.Http2Reader;

public class PostVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView uploader, text , like;
    public ImageView postImage;
    public PostAdapter postAdapter;
    public CheckBox likeImage, shareImage;


    public PostVIewHolder(@NonNull View itemView, PostAdapter postAdapter) {
        super(itemView);
        this.postAdapter= postAdapter;
        postImage = itemView.findViewById(R.id.iv_imageview);
        likeImage = itemView.findViewById(R.id.iv_likeimg);
        shareImage = itemView.findViewById(R.id.iv_shareimg);

        uploader = itemView.findViewById(R.id.tv_uploader);
        text = itemView.findViewById(R.id.tv_text);
        like = itemView.findViewById(R.id.tv_liketext);

        likeImage.setOnClickListener(this);
        shareImage.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        switch (view.getId()){
            case R.id.iv_likeimg :

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                postAdapter.clickEvent(getAdapterPosition(),"like");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    },500);

               break;
        }
    }


}


