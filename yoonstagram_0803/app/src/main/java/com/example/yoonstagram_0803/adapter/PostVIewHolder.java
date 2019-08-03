package com.example.yoonstagram_0803.adapter;

import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoonstagram_0803.R;

public class PostVIewHolder extends RecyclerView.ViewHolder {

    public TextView uploader, text , like;
    public ImageView likeImage, shareImage, postImage;
    public PostAdapter adapter;
    public PostVIewHolder(@NonNull View itemView, PostAdapter postAdapter) {
        super(itemView);
        this.adapter= postAdapter;
        postImage = itemView.findViewById(R.id.iv_imageview);
        likeImage = itemView.findViewById(R.id.iv_likeimg);
        shareImage = itemView.findViewById(R.id.iv_shareimg);

        uploader = itemView.findViewById(R.id.tv_uploader);
        text = itemView.findViewById(R.id.tv_text);
        like = itemView.findViewById(R.id.tv_liketext);


    }


}
