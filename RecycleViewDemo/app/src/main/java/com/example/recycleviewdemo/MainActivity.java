package com.example.recycleviewdemo;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recycleviewdemo.retrofit.NetworkClient;
import com.example.recycleviewdemo.retrofit.ReadInterface;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SportAdapter.Callback {
    String TAG = "메인엑티비티";
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    SportAdapter mSportAdapter;
    List<Sport> sports;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitGetList();
        //        prepareDemoContent();
       ButterKnife.bind(this);
       setUp();

    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mSportAdapter = new SportAdapter(new ArrayList<>());
        retrofitGetList();
        //prepareDemoContent();
    }


    private void retrofitGetList() {
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        ReadInterface readInterface = retrofit.create(ReadInterface.class);

        Call<List<Sport>> call = readInterface.getArticleList();

        call.enqueue(new Callback<List<Sport>>() {
            @Override
            public void onResponse(Call<List<Sport>> call, Response<List<Sport>> response) {

                sports = response.body();
                for (Sport sport :sports){
                    System.out.println(sport.toString());
                }
                System.out.println(sports.size() +" 리턴값 : ");
                mSportAdapter.addItems(sports);
                mRecyclerView.setAdapter(mSportAdapter);
            }

            @Override
            public void onFailure(Call<List<Sport>> call, Throwable t) {
                System.out.println("실패");

            }
        });


    }

    private void prepareDemoContent() {
        Log.d(TAG, "prepareDemoContent: START");
       // CommonUtils.showLoading(MainActivity.this);
        new Handler().postDelayed(() -> {
            //prepare data and show loading
         //   CommonUtils.hideLoading();
            List<Sport> mSports = new ArrayList<>();
            FetchList lists = new FetchList(this);
            try {
                mSports = lists.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, 3000);
        //CommonUtils.hideLoading();



    }

    @Override
    public void onEmptyViewRetryClick() {
        prepareDemoContent();
    }
}
