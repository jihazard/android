package com.example.ex0729_viewer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex0729_viewer.R;


public class PostItemViewHolder extends RecyclerView.ViewHolder {

    public TextView tvUserName ,tvPostText, tvLikeCount;
    public ImageView ivImg, ivLike, ivShare;

    public PostItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tvUserName = itemView.findViewById(R.id.tv_username);
        tvPostText = itemView.findViewById(R.id.tv_postText);
        tvLikeCount = itemView.findViewById(R.id.tv_postText);

        ivImg = itemView.findViewById(R.id.iv_imgView);
        ivLike = itemView.findViewById(R.id.iv_likeView);
        ivShare = itemView.findViewById(R.id.iv_shareView);

    }
}
