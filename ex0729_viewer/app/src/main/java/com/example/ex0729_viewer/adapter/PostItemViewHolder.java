package com.example.ex0729_viewer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex0729_viewer.R;


public class PostItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tvUserName ,tvPostText, tvLikeCount;
    public ImageView ivImg, ivLike, ivShare;
    public Adapter adapter;

    public PostItemViewHolder(@NonNull View itemView, Adapter adapter) {
        super(itemView);
        this.adapter=adapter;
        tvUserName = itemView.findViewById(R.id.tv_username);
        tvPostText = itemView.findViewById(R.id.tv_postText);
        tvLikeCount = itemView.findViewById(R.id.tv_postText);

        ivImg = itemView.findViewById(R.id.iv_imgView);
        ivLike = itemView.findViewById(R.id.iv_likeView);
        ivShare = itemView.findViewById(R.id.iv_shareView);

            ivLike.setOnClickListener(this);
            ivShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();

        switch (view.getId()){
            case R.id.iv_likeView:
                adapter.onLikeCliked(position,R.id.iv_likeView);
                break;
            case R.id.iv_shareView:
                adapter.onLikeCliked(position, R.id.iv_shareView);
                break;

        }
    }
}
