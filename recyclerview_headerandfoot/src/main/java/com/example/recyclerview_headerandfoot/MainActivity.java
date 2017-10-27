package com.example.recyclerview_headerandfoot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private List<BaseFragment> listFragment = new ArrayList<BaseFragment>();
    private List<String> listTitle = new ArrayList<String>();
    private MyFragmentAdapter mMyFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        listFragment.add(new HeaderAndFooterRecycleViewFragment_ListView());
        listTitle.add("ListView效果_HeaderAndFooter");
        listFragment.add(new HeaderAndFooterRecycleViewFragment_GridView());
        listTitle.add("GridView效果_HeaderAndFooter");
        listFragment.add(new HeaderAndFooterRecycleViewFragment_Staggered());
        listTitle.add("瀑布流效果_HeaderAndFooter");

        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mMyFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listTitle.get(position);
        }
    }
}
