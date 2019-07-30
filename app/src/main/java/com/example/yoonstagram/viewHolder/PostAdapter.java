package com.example.yoonstagram.viewHolder;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yoonstagram.R;
import com.example.yoonstagram.model.PostItem;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostItemViewHolder> {
    private Context context;
    private List<PostItem> items;

    public PostAdapter(Context context, List<PostItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.post_item, null);
        PostItemViewHolder pvh = new PostItemViewHolder(v, this);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostItemViewHolder holder, int position) {

        PostItem item = items.get(position);
        //holder.mainImg.setImageURI();
        holder.userName.setText(item.getUserName());
        holder.postText.setText(item.getPostText());
        holder.likeCount.setText(String.valueOf(item.getLikeCount()));
        Uri uri = Uri.parse(item.getImgUrl().toString());
        Glide.with(context)
                .load(uri)
                .into(holder.mainImg);




        }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void getClickEvent(int postion) {
        Toast.makeText(context, postion + " 의 좋아요가 클릭 됐습니다.", Toast.LENGTH_SHORT).show();
    }
}
