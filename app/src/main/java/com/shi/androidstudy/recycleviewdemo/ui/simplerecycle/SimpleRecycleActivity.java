package com.shi.androidstudy.recycleviewdemo.ui.simplerecycle;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.shi.androidstudy.recycleviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecycleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<String> list = new ArrayList<String>();
    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private MyAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycle);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

//        for (int i=0; i<8; i++){
//            list.add("标题"+i);
//            TestViewFragment testFragment = new TestViewFragment();
//            testFragment.initView(list.get(i));
//            listFragment.add(testFragment);
//        }

        list.add("ListView");
        list.add("GridView");
        list.add("水平GridView");
        list.add("瀑布流");
        listFragment.add(new ListViewRecycleViewFragment());
        listFragment.add(new GridViewRecycleViewFragment());
        listFragment.add(new HorizontalGridViewRecycleViewFragment());
        listFragment.add(new StaggeredRecycleFragment());


        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

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
