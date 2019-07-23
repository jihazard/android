package com.example.pagerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.vp_pager);

        Fragment[] arrFragment = new Fragment[3];
        List<Fragment>  fragments = new ArrayList<>();
        fragments.add(new RedFragment());
        fragments.add(new YellowFragment());
        fragments.add(new GreenFragment());
//        arrFragment[0] = new RedFragment();
//        arrFragment[1] = new YellowFragment();
//        arrFragment[2] = new GreenFragment();

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager() ,fragments);
        viewPager.setAdapter(myPagerAdapter);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

       // private  Fragment[] arrFragment;
        private  List<Fragment> list;
        public MyPagerAdapter(FragmentManager fm,  List<Fragment> arrFragment) {
            super(fm);
            this.list = arrFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
