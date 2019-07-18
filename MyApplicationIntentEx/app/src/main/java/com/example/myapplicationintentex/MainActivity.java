package com.example.myapplicationintentex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn_call);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Click!!!!", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_DIAL);
//        //intent.setAction(Intent.ACTION_CAMERA_BUTTON);
//        startActivity(intent);
        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        intent.putExtra("intent-message","하이 헬로우 제니퍼에요 ");
        startActivity(intent);
    }
}
