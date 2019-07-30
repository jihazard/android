package com.example.yoonstagram.viewHolder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoonstagram.R;

public class PostItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView userName , postText , likeCount;
    public  ImageView like , share , mainImg;
    public PostAdapter adapter;

    public PostItemViewHolder(@NonNull View itemView, PostAdapter postAdapter) {
        super(itemView);
        this.adapter = postAdapter;
        userName =itemView.findViewById(R.id.tv_username);
        postText = itemView.findViewById(R.id.tv_posttext);
        like = itemView.findViewById(R.id.iv_likeView);
        share = itemView.findViewById(R.id.iv_shareView);
        mainImg=itemView.findViewById(R.id.iv_imgView);
        likeCount = itemView.findViewById(R.id.tv_countView);

        like.setOnClickListener(this);
        share.setOnClickListener(this);


         }


    @Override
    public void onClick(View view) {
        int postion = getAdapterPosition();
        long itemId = getItemId();
        int itemViewType = getItemViewType();
        int layoutPosition = getLayoutPosition();

        Log.d("android get postion ", "onClick: " + postion +"/" + itemId +"/" + itemViewType  +"/" + layoutPosition);

        switch (view.getId()){
            case R.id.iv_likeView :
                adapter.getClickEvent(postion);
                break;
        }
    }
}
