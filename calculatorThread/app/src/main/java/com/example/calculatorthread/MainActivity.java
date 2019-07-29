package com.example.calculatorthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tv_display);

        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAsyncCal();
            }
        });
    }

    private void startAsyncCal() {
        //execute(1, 100000000);
        AsyncCal task = new AsyncCal();
        task.execute(1,Integer.MAX_VALUE);
    }

    class AsyncCal extends AsyncTask<Integer,Integer,Integer>{

        /*
        *  Async thread method
        * */
        @Override
        protected Integer doInBackground(Integer... integers) {

            int number = integers[0];
            int count = integers[1];
            int result = 0;

            int percentUnit = count /100;
            for (int i = 0; i < count ; i++) {
                result  +=number ;

                if(result % percentUnit == 0) {
                    publishProgress(result/percentUnit);
                }
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvDisplay.setText(values[0] +"  Percent");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvDisplay.setText("시작합니다.~");
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            tvDisplay.setText("취소되었습니다." + integer);
        }



        /*
        *  Ui thread
        * */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tvDisplay.setText("result : " + integer);
        }
    }


}
