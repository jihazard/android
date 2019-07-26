package com.example.recycleview03.recycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview03.R;

import org.w3c.dom.Text;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivImg, ivLike, ivShare;
    public TextView  tvLikeCount ,tvUserName, tvPostText;


    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        ivImg = itemView.findViewById(R.id.iv_img);
        ivLike = itemView.findViewById(R.id.iv_like);
        ivShare = itemView.findViewById(R.id.iv_share);
        tvLikeCount = itemView.findViewById(R.id.likeCount);
        tvUserName = itemView.findViewById(R.id.tv_username);
        tvPostText = itemView.findViewById(R.id.tv_posttext);

    }
}
