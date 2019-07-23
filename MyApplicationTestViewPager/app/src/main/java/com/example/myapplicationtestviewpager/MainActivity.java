package com.example.myapplicationtestviewpager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs);
         ViewPager viewPager = findViewById(R.id.viewPager);
        List<Fragment>  fragments = new ArrayList<>();
        fragments.add(new Red());
        fragments.add(new Yellow());
        fragments.add(new Blue());


        FragmentAdapter myPagerAdapter = new FragmentAdapter(getSupportFragmentManager() ,fragments);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> lists;
        public FragmentAdapter(FragmentManager fm, List<Fragment> lists) {
            super(fm);
            this.lists = lists;
        }

        @Override
        public Fragment getItem(int position) {
           return lists.get(position);
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 :
                    return "RED";

                case 1 :
                    return "YELLOW";

                case 2 :
                    return "BLUE";

                default:
                    return "xx";
            }
        }
    }
}
