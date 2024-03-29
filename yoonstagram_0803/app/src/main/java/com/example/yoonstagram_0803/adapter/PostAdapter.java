package com.example.yoonstagram_0803.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yoonstagram_0803.MainActivity;
import com.example.yoonstagram_0803.R;
import com.example.yoonstagram_0803.api.API;
import com.example.yoonstagram_0803.api.LikeAsync;
import com.example.yoonstagram_0803.model.PostItem;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostAdapter extends RecyclerView.Adapter<PostVIewHolder> {
    public List<PostItem> lists;
    public Context context;
    public RecyclerView rv;

    public PostAdapter(MainActivity mainActivity, List<PostItem> data, RecyclerView rv) {
        lists = data;
        this.rv = rv;
    }

    public PostAdapter(Context context, List<PostItem> data) {
        lists = data;
        this.context = context;
    }

    @NonNull
    @Override
    public PostVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = View.inflate(context, R.layout.post_item, null);
        PostVIewHolder pvh = new PostVIewHolder(v, this);
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

    public void clickEvent(int postion, String id) throws IOException {
        PostItem postItem = lists.get(postion);
        Toast.makeText(context, postItem.getUploader() + "의 " + postion + " 번째 게시물 클릭", Toast.LENGTH_SHORT).show();
        try {

            updateLike(postItem, postion);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void updateLike(PostItem postItem, int postion) throws  ExecutionException, InterruptedException {
        LikeAsync likeAsync = new LikeAsync();

        Integer like = (Integer) postItem.getLikes();
        Boolean aBoolean = likeAsync.execute((int) (long) postItem.getId(), like).get();
        if (aBoolean) {

            postItem.setLikes(postItem.getLikes() + 1);
            int updateIndex = postion ;
            lists.set(updateIndex, postItem);
            this.notifyItemChanged(updateIndex);

        } else {
            Log.d("태그실패", "updateLike: " + "라이크 추가 실패");
            Toast.makeText(context, "포스트 삭제", Toast.LENGTH_SHORT).show();
            lists.remove(postion);
            this.notifyItemRemoved(postion);


        }
    }


}
