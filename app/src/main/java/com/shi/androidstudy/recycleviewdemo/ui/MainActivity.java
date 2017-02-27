package com.shi.androidstudy.recycleviewdemo.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.ui.complexrecycle.HeaderAndFooterRecycleViewFragment;
import com.shi.androidstudy.recycleviewdemo.ui.complexrecycle.RefreshAndLoadRecycleViewFragment;
import com.shi.androidstudy.recycleviewdemo.ui.complexrecycle.RefreshRecycleViewFragment;
import com.shi.androidstudy.recycleviewdemo.ui.simplerecycle.GridViewRecycleViewFragment;
import com.shi.androidstudy.recycleviewdemo.ui.simplerecycle.HorizontalGridViewRecycleViewFragment;
import com.shi.androidstudy.recycleviewdemo.ui.simplerecycle.ListViewRecycleViewFragment;
import com.shi.androidstudy.recycleviewdemo.ui.simplerecycle.StaggeredRecycleFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<String> list = new ArrayList<String>();
    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private MyAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        list.add("ListView");
        list.add("GridView");
        list.add("水平GridView");
        list.add("瀑布流");
        list.add("下拉刷新ListView");
        list.add("下拉刷新上拉加载ListView");
        list.add("顶部和底部ListView");
        listFragment.add(new ListViewRecycleViewFragment());
        listFragment.add(new GridViewRecycleViewFragment());
        listFragment.add(new HorizontalGridViewRecycleViewFragment());
        listFragment.add(new StaggeredRecycleFragment());
        listFragment.add(new RefreshRecycleViewFragment());
        listFragment.add(new RefreshAndLoadRecycleViewFragment());
        listFragment.add(new HeaderAndFooterRecycleViewFragment());

        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
    }

    private class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
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
            return list.get(position );
        }
    }
}
