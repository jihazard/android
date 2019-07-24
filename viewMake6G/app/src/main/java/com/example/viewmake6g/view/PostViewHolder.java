package com.example.viewmake6g.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmake6g.PostAdapter;
import com.example.viewmake6g.R;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView ivImg, ivLike,ivShare;
    public TextView tvLikeCount, tvUserName, tvPostText;
    public PostAdapter postAdapter;

    public PostViewHolder(@NonNull View itemView , PostAdapter postAdapter) {
        super(itemView);
        ivImg = itemView.findViewById(R.id.iv_img);
        ivLike = itemView.findViewById(R.id.iv_like);
        ivShare = itemView.findViewById(R.id.iv_share);
        tvLikeCount = itemView.findViewById(R.id.iv_count);
        tvUserName = itemView.findViewById(R.id.tv_UserName);
        tvPostText = itemView.findViewById(R.id.tv_PostText);
        this.postAdapter = postAdapter;

    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        switch (view.getId()){
            case R.id.iv_like:
                postAdapter.onClikcked(position);
            case R.id.iv_share:
                break;

        }
    }
}
