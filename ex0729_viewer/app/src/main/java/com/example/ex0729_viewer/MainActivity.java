package com.example.ex0729_viewer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.SingleRequest;
import com.example.ex0729_viewer.API.Api;
import com.example.ex0729_viewer.API.FetchApi;
import com.example.ex0729_viewer.adapter.Adapter;
import com.example.ex0729_viewer.model.Post;
import com.example.ex0729_viewer.model.PostItem;
import com.example.ex0729_viewer.model.Records;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            RecyclerView rv = findViewById(R.id.view_recycler);
            List<PostItem> items = new ArrayList<>();
            List<Post> posts =fetchAsyncPosts();



//            for (int i = 0; i < 15; i++) {
//
//                String url = "";
//                if(i % 2 == 0)  url = "http://file3.instiz.net/data/file3/2019/07/21/d/e/1/de13e93a7015cbc04d6c73635c5dea6e.jpg";
//                else  url = url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQfeEiebccCvamOSlqIZrTb3TuP2boFTLFMOi5CTzi7PnvNoX8";
//
//
//                PostItem item = new PostItem().builder()
//                        .userName("yoonjh238")
//                        .postText("안녕하세요")
//                        .imgUrl(url)
//                        .isLike(true)
//                        .likeCount(i).build();
//
//                Log.d("IMAGEURL", "onCreate: " + url);
//
//                items.add(item);
//            }

        Intent intent = getIntent();

        if(intent.getStringExtra("data")!=null){
            Log.d("intent", "onCreate() called with: savedInstanceState = [" + intent.getStringExtra("data") + "]");
            String text = intent.getStringExtra("data");
            Uri uri =  intent.getData();
            PostItem item = new PostItem().builder()
                    .userName(text)
                    .postText(text)
                    .imgUrl(uri.toString())
                    .isLike(true)
                    .likeCount(1).build();
            items.add(item);

        }


        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            rv.setAdapter(new Adapter(this,posts));

        View btn = findViewById(R.id.fab_post);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                }
            }
        });

    }
    public List<Post> fetchAsyncPosts() {
        List<Post> posts = new ArrayList<>();
        FetchApi fetchPostTask = new FetchApi();
        try {
            String string = fetchPostTask.execute(Api.BASE_URL).get();

            /*
            * JacksonCore
            * */
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            List<Post> convertWebSiteModels = mapper.readValue(string, new TypeReference<List<Post>>(){});

            /*
            * Gson
            * */
            Gson gson = new Gson();
            Post[] posts1 = gson.fromJson(string, Post[].class);
            List<Post> posts2 = Arrays.asList(posts1);

            Log.d("post", "fetchAsyncPosts: " +convertWebSiteModels.toString() );
            return posts2;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            Bundle extras = data.getExtras();
            Log.d("onActivityResult" , " camera sucees ss " + resultCode +"//" + requestCode);
            /*
            * 데이터 넘겨주기 activity --> postactivity로
            * */

            Intent startintent = new Intent(this,PostActivity.class);
            startintent.setData(data.getData());
            startActivity(startintent);
 //           Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ImageView imageView = null;
//            imageView.setImageBitmap(imageBitmap);


        }
    }
}
