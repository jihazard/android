package com.example.yoonstagram_0803.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yoonstagram_0803.MainActivity;
import com.example.yoonstagram_0803.R;
import com.example.yoonstagram_0803.model.PostItem;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostVIewHolder> {
    public List<PostItem> lists;
    public  Context context;

    public PostAdapter( List<PostItem> data) {
    lists= data;
    }public PostAdapter(Context context , List<PostItem> data) {
    lists= data;
    this.context = context;
    }

    @NonNull
    @Override
    public PostVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = View.inflate(context, R.layout.post_item, null);
        PostVIewHolder pvh = new PostVIewHolder(v,this);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostVIewHolder holder, int position) {
        PostItem postItem = lists.get(position);
        holder.uploader.setText(postItem.getUploader());
        holder.text.setText(postItem.getText());
        holder.like.setText(String.valueOf(postItem.getLikes()));

        Glide.with(context)
                .load(postItem.getImage())
                .into(holder.postImage);


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
