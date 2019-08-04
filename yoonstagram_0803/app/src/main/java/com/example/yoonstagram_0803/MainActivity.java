package com.example.yoonstagram_0803;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yoonstagram_0803.adapter.PostAdapter;
import com.example.yoonstagram_0803.api.API;
import com.example.yoonstagram_0803.api.FetchAsync;
import com.example.yoonstagram_0803.model.PostItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Uri uri;
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
//         사진권한
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        };
//
//        TedPermission.with(this)
//                .setPermissionListener(permissionlistener)
//                .setRationaleMessage("사진권한이 필요합니다.")
//                .setDeniedMessage("왜 거부하셨어요...\n하지만 [설정] > [권한] 에서 권한을 허용할 수 있어요.")
//                .setPermissions(Manifest.permission.READ_CONTACTS)
//                .check();



        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
           startActivityForResult(takePictureIntent, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (Build.VERSION.SDK_INT >= 23) {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Log.v("permission","Permission: "+permissions[0]+ "was "+grantResults[0]);
                //resume tasks needing this permission


            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();

            Log.d("onActivityResult", " camera sucees ss " + resultCode + "//" + requestCode);
            Log.d("onActivityResult", " camera sucees ss " + uri + "//" + data.getData());


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
