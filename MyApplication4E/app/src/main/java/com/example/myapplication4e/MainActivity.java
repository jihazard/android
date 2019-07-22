package com.example.myapplication4e;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewImg = findViewById(R.id.iuImg);
        viewImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "THIS IS IU!!!!!!!", Toast.LENGTH_SHORT).show();

    }
}
