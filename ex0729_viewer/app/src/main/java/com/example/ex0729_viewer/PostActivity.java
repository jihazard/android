package com.example.ex0729_viewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        ImageView iv = findViewById(R.id.iv_postx);


        Intent intent = getIntent();
        Uri uri = intent.getData();

        iv.setImageURI(uri);
//        Bundle extra = getIntent().getExtras();
//        Bitmap imap = (Bitmap) extra.get("data");
//        iv.setImageBitmap(imap);


        View rid = findViewById(R.id.btn_back);
        rid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
