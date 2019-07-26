package com.example.recycleviewex_02.recycleView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewex_02.R;

import org.w3c.dom.Text;

import lombok.Getter;
import lombok.Setter;

public class AdapterViewHolder extends RecyclerView.ViewHolder {


    public ImageView ivImgMain , ivLike, ivShare;
    public TextView tvLikeCount , tvUserName, tvPostText;


    public AdapterViewHolder(@NonNull View itemView) {
        super(itemView);

         ivImgMain = itemView.findViewById(R.id.iv_imgview);
         ivLike = itemView.findViewById(R.id.iv_likeImg);
         ivShare = itemView.findViewById(R.id.iv_share);


         tvLikeCount = itemView.findViewById(R.id.tv_likeCount);
         tvUserName = itemView.findViewById(R.id.tv_username);
         tvPostText = itemView.findViewById(R.id.tv_posttext);


    }
}
