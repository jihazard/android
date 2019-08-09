package com.example.ex0729_viewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ex0729_viewer.API.Api;
import com.example.ex0729_viewer.R;
import com.example.ex0729_viewer.model.Post;
import com.example.ex0729_viewer.model.PostItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Adapter extends RecyclerView.Adapter<PostItemViewHolder> {
    private Context context;
    private List<PostItem> postItems;
    private List<Post> posts;
    private Bitmap bmp;

    public Adapter(List<Post> posts) {
          this.posts = posts;
    }
    public Adapter(Context context, List<Post> posts) {
          this.posts = posts;
    }

    @NonNull
    @Override
        public PostItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        View v = View.inflate(context, R.layout.post_item, null);
        PostItemViewHolder view = new PostItemViewHolder(v,this);

        return view;
    }






    @Override
    public void onBindViewHolder(@NonNull PostItemViewHolder holder, int position) {

        Post item = posts.get(position);
        holder.tvUserName.setText(item.getUploader());
        holder.tvPostText.setText(item.getText());
        holder.tvLikeCount.setText(String.valueOf(item.getLikes()));
        String url = "";
        Uri uri =  Uri.parse(item.getImage());

        Glide.with(context)
                .load(uri)
                .centerCrop()
                .into(holder.ivImg);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void onLikeCliked(int position, int ids) {

        String select = ids == R.id.iv_likeView ? "좋아요" : "쉐어";
        Toast.makeText(context, select +"//"+position +"번호를 클릭했습니다.", Toast.LENGTH_SHORT).show();
    }
}
