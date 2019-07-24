package com.example.viewmake6g;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmake6g.model.PostItem;
import com.example.viewmake6g.view.PostViewHolder;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context mContext;
    private List<PostItem> lists;
    public PostAdapter(Context mContext, List<PostItem> lists) {
        this.mContext = mContext;
        this.lists = lists;

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View baseView = View.inflate(mContext, R.layout.post_item, null);
            PostViewHolder postViewHolder = new PostViewHolder(baseView);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostItem item = lists.get(position);
        holder.tvUserName.setText(item.getUserName());
        holder.tvPostText.setText(item.getPostText());
        holder.tvLikeCount.setText(String.valueOf(item.getPostLikeCount()));


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
