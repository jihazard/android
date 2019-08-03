package com.example.yoonstagram_0803;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yoonstagram_0803.adapter.PostAdapter;
import com.example.yoonstagram_0803.api.API;
import com.example.yoonstagram_0803.api.FetchAsync;
import com.example.yoonstagram_0803.model.PostItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recycle_view);
        FloatingActionButton cameraBtn = findViewById(R.id.figureBtn);


        try {
            List<PostItem> data = getData();

            rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            rv.setAdapter(new PostAdapter(this, data));

            cameraBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dispatchTakePictureIntent();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }

      }

    private List<PostItem> getData()  {
        FetchAsync fetchAsync = new FetchAsync();
        try {
            List<PostItem> postItems = fetchAsync.execute(API.READ_API_URL).get();
            return postItems;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Log.d("onActivityResult", " camera sucees ss " + resultCode + "//" + requestCode);
            /*
             * 데이터 넘겨주기 activity --> postactivity로
             * */

            Intent startintent = new Intent(this, CameraActivity.class);
            startintent.setData(data.getData());
            startActivity(startintent);

            //           Bitmap imageBitmap = (Bitmap) extras.get("data");
//            ImageView imageView = null;
//            imageView.setImageBitmap(imageBitmap);


        }
    }
}
