package com.example.recycleviewex_02.recycleView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewex_02.R;
import com.example.recycleviewex_02.model.PostItem;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
    private Context  context;
    private List<PostItem> items ;

    public PostAdapter(Context context, List<PostItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = View.inflate(context, R.layout.post_item, null);
        AdapterViewHolder viewHolder = new AdapterViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        PostItem item = items.get(position);

        holder.tvUserName.setText(item.username);
        holder.tvPostText.setText(item.postText);
        holder.tvLikeCount.setText(String.valueOf(item.postLikeCount));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
