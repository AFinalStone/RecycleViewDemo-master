package com.shi.androidstudy.recycleviewdemo.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.ui.gridview.HeaderAndFooterRecycleViewFragment_GridView;
import com.shi.androidstudy.recycleviewdemo.ui.gridview.RefreshRecycleViewFragment_GridView;
import com.shi.androidstudy.recycleviewdemo.ui.listview.HeaderAndFooterRecycleViewFragment_ListView;
import com.shi.androidstudy.recycleviewdemo.ui.gridview.RefreshAndLoadRecycleViewFragment_GridView;
import com.shi.androidstudy.recycleviewdemo.ui.listview.HeaderAndFooterRefreshAndLoadRecycleViewFragment_ListView;
import com.shi.androidstudy.recycleviewdemo.ui.listview.RefreshAndLoadRecycleViewFragment_ListView;
import com.shi.androidstudy.recycleviewdemo.ui.listview.RefreshRecycleViewFragment_ListView;
import com.shi.androidstudy.recycleviewdemo.ui.gridview.RecycleViewFragment_GridView;
import com.shi.androidstudy.recycleviewdemo.ui.gridview.RecycleViewFragment_HorizontalGridView;
import com.shi.androidstudy.recycleviewdemo.ui.listview.RecycleViewFragment_ListView;
import com.shi.androidstudy.recycleviewdemo.ui.staggered.HeaderAndFooterRecycleViewFragment_Staggered;
import com.shi.androidstudy.recycleviewdemo.ui.staggered.RefreshAndLoadRecycleViewFragment_Staggered;
import com.shi.androidstudy.recycleviewdemo.ui.staggered.RefreshRecycleViewFragment_Staggered;
import com.shi.androidstudy.recycleviewdemo.ui.staggered.StaggeredRecycleFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<String> list = new ArrayList<String>();
    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private MyAdapter adapter;
    private TabLayout tabLayout;

    public static final String TypeRecycler_ListView = "ListView";
    public static final String TypeRecycler_GridView = "GridView";
    public static final String TypeRecycler_Staggered = "Staggered";
    public static final String TypeRecycler = "TypeRecycler";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        String type = getIntent().getStringExtra(TypeRecycler);
        if(TypeRecycler_ListView.equals(type)){
            //listView控件
            list.add("ListView");
            listFragment.add(new RecycleViewFragment_ListView());
            list.add("下拉刷新ListView");
            listFragment.add(new RefreshRecycleViewFragment_ListView());
            list.add("下拉刷新上拉加载ListView");
            listFragment.add(new RefreshAndLoadRecycleViewFragment_ListView());
            list.add("顶部和底部ListView");
            listFragment.add(new HeaderAndFooterRecycleViewFragment_ListView());
            list.add("顶部和底部且可刷新的ListView");
            listFragment.add(new HeaderAndFooterRefreshAndLoadRecycleViewFragment_ListView());
        }else if(TypeRecycler_GridView.equals(type)){
            //girdView控件
            list.add("GridView");
            listFragment.add(new RecycleViewFragment_GridView());
            list.add("水平GridView");
            listFragment.add(new RecycleViewFragment_HorizontalGridView());
            list.add("下拉刷新GridView");
            listFragment.add(new RefreshRecycleViewFragment_GridView());
            list.add("下拉刷新上拉加载GridView");
            listFragment.add(new RefreshAndLoadRecycleViewFragment_GridView());
            list.add("顶部和底部GridView");
            listFragment.add(new HeaderAndFooterRecycleViewFragment_GridView());
        }else if(TypeRecycler_Staggered.equals(type)){
            list.add("瀑布流");
            listFragment.add(new StaggeredRecycleFragment());
            list.add("下拉刷新瀑布流");
            listFragment.add(new RefreshRecycleViewFragment_Staggered());
            list.add("下拉刷新上拉加载瀑布流");
            listFragment.add(new RefreshAndLoadRecycleViewFragment_Staggered());
            list.add("顶部和底部瀑布流");
            listFragment.add(new HeaderAndFooterRecycleViewFragment_Staggered());
        }

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
