package com.example.pageviewrtest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.vp_viewPager);

        List<Fragment> lists = Arrays.asList(new RedFragment(), new YellowFragment(), new BlueFragment());
        TabLayout tabLayout = findViewById(R.id.vp_tabs);

        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager(), lists));
        tabLayout.setupWithViewPager(viewPager);
    }

    private class AdapterFragment extends FragmentPagerAdapter {

        private List<Fragment> lists;

        public AdapterFragment(FragmentManager fm, List<Fragment> lists) {
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

            switch (position) {
                case 0:
                    return "RED";
                case 1:
                    return "YELLOW";
                case 2:
                    return "BLUE";
                default:
                    return "";
            }


        }
    }
}
