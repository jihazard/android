package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnColorButtonListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onColorClick(int color) {
        Fragment fragment = null;
        switch (color){
            case 0 :
                fragment = new RedFragment();
                break;
            case 1 :
                fragment = new BlueFragment();
                break;
            case 2 :

                fragment = new GreenFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_right,fragment).commit();
    }
}
