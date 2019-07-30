package com.example.ex0729_viewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ex0729_viewer.R;
import com.example.ex0729_viewer.model.PostItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<PostItemViewHolder> {
    private Context context;
    private List<PostItem> postItems;
    private Bitmap bmp;

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

        Log.d("image url 1/2 ", "onBindViewHolder: " + item.getImgUrl());
        String url = "";
        if(item.getImgUrl().equals("1"))  url = "http://file3.instiz.net/data/file3/2019/07/21/d/e/1/de13e93a7015cbc04d6c73635c5dea6e.jpg";
        else if(item.getImgUrl().equals("2")) url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";
        else url = item.getImgUrl();
        Log.d("image url 1/2 ", "onBindViewHolder: " + url);
        Uri uri =  Uri.parse(url);
        InputStream in = null;
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(holder.ivImg);



        //   if(i % 2 == 0) url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";
     //   else  url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";
      //  Uri uri =  Uri.parse( item.getImgUrl());
      //  holder.ivImg.setImageURI(uri);
//        Glide.with(context)
//                .load(item.getImgUrl())
//                .centerCrop()
//                .into(holder.ivImg);
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
