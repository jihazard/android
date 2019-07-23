package com.example.sharedpreferences5g;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //default = -1
    //user = 1

    public static final String SPREF_FIRST_USER_KEY = "1000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_po);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        textView.setOnClickListener(this);

        int firstUser = sharedPref.getInt(SPREF_FIRST_USER_KEY ,-1);

        if(firstUser == 1){
            //기존유저
            textView.setText(getString(R.string.hello_twice));
        }else if(firstUser == -1){
            //최초유저
            textView.setText(getString(R.string.hello_first));
            saveUserIsNotFirst();
        }

        }

    private void saveUserIsNotFirst() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SPREF_FIRST_USER_KEY, 1);
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show();
    }
}
