package com.example.recycleviewdemo;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SportAdapter.Callback {
    String TAG = "메인엑티비티";
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    SportAdapter mSportAdapter;

    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareDemoContent();
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

        prepareDemoContent();
    }

    private void prepareDemoContent() {
        Log.d(TAG, "prepareDemoContent: START");
       // CommonUtils.showLoading(MainActivity.this);
        new Handler().postDelayed(() -> {
            //prepare data and show loading
         //   CommonUtils.hideLoading();
            List<Sport> mSports = new ArrayList<>();
            FetchList lists = new FetchList();
            try {
                mSports = lists.execute(Api.READ_API).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mSportAdapter.addItems(mSports);
            mRecyclerView.setAdapter(mSportAdapter);
        }, 1000);
        //CommonUtils.hideLoading();



    }

    @Override
    public void onEmptyViewRetryClick() {
        prepareDemoContent();
    }
}
