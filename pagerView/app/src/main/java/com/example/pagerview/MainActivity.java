package com.example.pagerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.vp_pager);

        Fragment[] arrFragment = new Fragment[3];
        arrFragment[0] = new RedFragment();
        arrFragment[1] = new YellowFragment();
        arrFragment[2] = new GreenFragment();

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager() ,arrFragment);
        viewPager.setAdapter(myPagerAdapter);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

        private  Fragment[] arrFragment;

        public MyPagerAdapter(FragmentManager fm, Fragment[] arrFragment) {
            super(fm);
            this.arrFragment = arrFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragment[position];
        }

        @Override
        public int getCount() {
            return arrFragment.length;
        }
    }
}
