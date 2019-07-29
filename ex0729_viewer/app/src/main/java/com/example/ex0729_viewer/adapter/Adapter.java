package com.example.ex0729_viewer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ex0729_viewer.R;
import com.example.ex0729_viewer.model.PostItem;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<PostItemViewHolder> {
    private Context context;
    private List<PostItem> postItems;


    public Adapter(Context context, List<PostItem> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    @NonNull
    @Override
    public PostItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.post_item, null);
        PostItemViewHolder view = new PostItemViewHolder(v,this);

        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull PostItemViewHolder holder, int position) {

        PostItem item = postItems.get(position);
        holder.tvUserName.setText(item.getUserName());
        holder.tvPostText.setText(item.getPostText());
        holder.tvLikeCount.setText(String.valueOf(item.getLikeCount()));

        Glide.with(context)
                .load(item.getImgUrl())
                .centerCrop()
                .into(holder.ivImg);
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public void onLikeCliked(int position, int ids) {

        String select = ids == R.id.iv_likeView ? "좋아요" : "쉐어";
        Toast.makeText(context, select +"//"+position +"번호를 클릭했습니다.", Toast.LENGTH_SHORT).show();
    }
}
