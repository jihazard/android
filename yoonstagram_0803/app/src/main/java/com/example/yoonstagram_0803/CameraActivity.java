package com.example.yoonstagram_0803;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageView imageview = findViewById(R.id.iv_takeImg);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        Log.d("uri", "onCreate: " + uri);
        Glide.with(this)
                .load(uri)
                .into(imageview);



    }

    public Bitmap getBitmap(Uri uri ) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri,"r");

        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();

        return image;
    }

    public File createFileFromBitmap(Bitmap bitmap) throws FileNotFoundException {
        File newfile = new File(getFilesDir(),"");
        FileOutputStream fileOutputStream = new FileOutputStream(makeImagePath());
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        return newfile;
    }

    public String makeImagePath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        Date date = new Date();
        String strDate = simpleDateFormat.format(date);
        return strDate +".png";
    }
}
