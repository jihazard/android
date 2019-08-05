package com.example.yoonstagram_0803;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.yoonstagram_0803.api.API;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.yoonstagram_0803.api.API.KEY_ACTION;
import static com.example.yoonstagram_0803.api.API.KEY_ID;
import static com.example.yoonstagram_0803.api.API.KEY_IMAGE;
import static com.example.yoonstagram_0803.api.API.KEY_NAME;

public class CameraActivity extends AppCompatActivity {
    EditText et;
    ImageView imageview;
    String image;
    Uri uri;
    Context context;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageview = findViewById(R.id.iv_takeImg);
        et = findViewById(R.id.postText);
        Button postBtn = findViewById(R.id.postBtn);
        context = getApplicationContext();
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                addUser();
               //  post(image )
                try {
                    post(uri.toString(),et.getText().toString());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Intent intent = getIntent();
        uri = intent.getData();

        Log.d("uri", "onCreate: " + uri);
        Glide.with(this)
                .load(uri)
                .into(imageview);

        try {
            Bitmap bitmap = getBitmap(uri);
            image = getStringImage(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void post (String uriString ,String textString) throws ExecutionException, InterruptedException {

        postAsync postAsync = new postAsync();
        Boolean aBoolean = postAsync.execute(uriString, textString).get();


    }
    class postAsync extends AsyncTask<String,Void,Boolean>{


        public postAsync() {
        }

        @Override
        protected void onPreExecute() {
            Log.d("프리", "onPreExecute: " + "프리 실행됩니까?");

            super.onPreExecute();
            progressDialog = new ProgressDialog(CameraActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loding wait.");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            progressDialog.show();

        }

        @Override
        protected Boolean doInBackground(String... strings) {

            Uri uri = Uri.parse(strings[0]);
            String text = strings[1];

            try {

                Bitmap bitmap = getBitmap(uri);
                File file = createFileFromBitmap(bitmap);

                RequestBody body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("action","insert")
                        .addFormDataPart("uploader","yoonjh238")
                        .addFormDataPart("text", text)
                        .addFormDataPart("image", image)
                        .build();


                Request request = new Request.Builder()
                        .url(API.WRITE_API_URL)
                        .post(body)
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20,TimeUnit.SECONDS)
                        .writeTimeout(20,TimeUnit.SECONDS)
                        .build();
                Response result = client.newCall(request).execute();

                if(result.body().string().contains("success")){
                    Log.d("return", "doInBackground: " + "전송성공");
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Error", "doInBackground: " + e.getMessage());
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean){
                Log.d("tag", "onPostExecute: " + "투루입니다.");
                Toast.makeText(CameraActivity.this, "success", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }else{
                Log.d("tag", "onPostExecute: " + "펄스입니다.");

            }
//            if(aBoolean) {
//                Log.d("tab", "onPostExecute: " + "success");
//
//            }else Log.d("tab", "onPostExecute: " + "false");


        }
    }


    public Bitmap getBitmap(Uri uri ) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri,"r");

        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;


        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor,null,opt);
        float ratio = getResizedFromOriginalBitmap(opt);
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = (int) ratio;

        Bitmap resized = BitmapFactory.decodeFileDescriptor(fileDescriptor,null,opt);

        parcelFileDescriptor.close();

        return resized;
    }

    public float getResizedFromOriginalBitmap( BitmapFactory.Options  opt) {

        int width = opt.outWidth;
        int height = opt.outHeight;

        int targetWidth = 1280;
        int targetHeight = 1280;

        float ratio;

        if(width>height){
            if(width> targetWidth){
                ratio = (float)width /(float)targetWidth;
            }else ratio = 1f;
        }else{
            if(height > targetHeight){
                ratio = (float)height /(float)targetHeight;
            }else ratio =1f;
        }


        return Math.round(ratio);
    }

    public File createFileFromBitmap(Bitmap bitmap) throws FileNotFoundException {
        File newfile = new File(getFilesDir(),makeImagePath());
        FileOutputStream fileOutputStream = new FileOutputStream(newfile);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        return newfile;
    }

    public String makeImagePath() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        Date date = new Date();
        String strDate = simpleDateFormat.format(date);
        return strDate +".jpg";
    }


    public File createFile() throws FileNotFoundException {
        String fileName = makeImagePath();
        FileOutputStream fos = openFileOutput(fileName, this.MODE_PRIVATE);
        try {
            fos.write(image.getBytes());
            fos.close();
           return new File(getFilesDir(),"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }






    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void addUser(){
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        final String userId = "yoonjh238";
        final String userName = et.getText().toString().trim();
        //Bitmap  rbitmap = getResizedBitmap(bitmap,500);

        Log.e("null","values"+imageview);

        OkHttpClient client = new OkHttpClient();
        try {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("uploader",userId)
                    .addFormDataPart("text", et.getText().toString())
                    .addFormDataPart("image",image, RequestBody.create(MultipartBody.FORM,createFile())).build();

            Request request = new Request.Builder()
                    .url(API.WRITE_API_URL)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d("error", "onFailure: " + e.getMessage());
                    Toast.makeText(CameraActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.d("success", "onResponse: " + response.body().toString());
                    Toast.makeText(CameraActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


//        VOLLY
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, API.WRITE_API_URL,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        loading.dismiss();
//                        Log.d("Response log", "onResponse: " + response);
//                        Toast.makeText(CameraActivity.this, response, Toast.LENGTH_LONG).show();
//                        Intent startintent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(startintent);
//                    }
//                },
//                new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("error.toString() log", "onResponse: " + error.toString());
//                        Toast.makeText(CameraActivity.this,error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }
//                ){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("uploader",userId);
//                params.put("text",et.getText().toString());
//                params.put("image",image);
//
//                return params;
//            }
//
//        };
//
//        int socketTimeout = 30000; // 30 seconds. You can change it
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//
//        stringRequest.setRetryPolicy(policy);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);

    }





}
