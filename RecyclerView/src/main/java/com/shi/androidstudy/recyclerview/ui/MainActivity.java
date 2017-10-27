package com.shi.androidstudy.recyclerview.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.shi.androidstudy.recyclerview.R;
import com.shi.androidstudy.recyclerview.ui.gridview.RecyclerViewFragment_GridView;
import com.shi.androidstudy.recyclerview.ui.gridview.RecyclerViewFragment_HorizontalGridView;
import com.shi.androidstudy.recyclerview.ui.listview.RecyclerViewFragment_ListView;
import com.shi.androidstudy.recyclerview.ui.staggered.RecyclerViewFragment_Staggered;

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
        listFragment.add(new RecyclerViewFragment_ListView());
        listTitle.add("ListView效果");
        listFragment.add(new RecyclerViewFragment_GridView());
        listTitle.add("GridView效果");
        listFragment.add(new RecyclerViewFragment_HorizontalGridView());
        listTitle.add("水平GridView效果");
        listFragment.add(new RecyclerViewFragment_Staggered());
        listTitle.add("瀑布流效果");
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
