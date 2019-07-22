package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.left_red).setOnClickListener(this);
        findViewById(R.id.left_blue).setOnClickListener(this);
        findViewById(R.id.left_green).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        RedFragment redFragment = new RedFragment();
        BlueFragment blueFragment = new BlueFragment();
        GreenFragment greenFragment = new GreenFragment();
        switch (view.getId()){
            case R.id.left_red :
                Toast.makeText(this, "This is red panel", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_right,redFragment).commit();
                break;
            case R.id.left_blue :
                Toast.makeText(this, "This is blue panel", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_right,blueFragment).commit();
                break;
            case R.id.left_green:
                Toast.makeText(this, "This is green panel", Toast.LENGTH_SHORT).show();

                getSupportFragmentManager().beginTransaction().add(R.id.fl_right,greenFragment).commit();
                break;
        }
    }
}
