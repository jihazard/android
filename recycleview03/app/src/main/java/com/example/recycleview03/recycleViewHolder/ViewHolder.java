package com.example.recycleview03.recycleViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview03.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivmainimg , ivlike, ivshare;
    private TextView username, postText , postCount;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        ivmainimg = itemView.findViewById(R.id.iv_imgView);
        ivlike = itemView.findViewById(R.id.iv_likeImg);
        ivshare = itemView.findViewById(R.id.iv_share);

        username = itemView.findViewById(R.id.tv_username);
        postText = itemView.findViewById(R.id.tv_posttext);
        postCount = itemView.findViewById(R.id.iv_likeCount);


    }
}
