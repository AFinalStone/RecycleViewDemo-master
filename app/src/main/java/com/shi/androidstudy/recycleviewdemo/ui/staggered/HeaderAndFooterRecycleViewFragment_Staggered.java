package com.shi.androidstudy.recycleviewdemo.ui.staggered;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.adapter.GeneralAdapter;
import com.shi.androidstudy.recycleviewdemo.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.shi.androidstudy.recycleviewdemo.adapter.StaggeredAdapter;
import com.shi.androidstudy.recycleviewdemo.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 带头部和尾部的控件
 */
public class HeaderAndFooterRecycleViewFragment_Staggered extends BaseFragment{

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private StaggeredAdapter staggeredAdapter;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_simple_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();

        for (int i = 'A'; i < 'Z'; i++) {
            listData.add("" + (char) i);
        }
        //StaggeredGridLayoutManager，实现瀑布流效果,每行展示3个item
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));

        staggeredAdapter = new StaggeredAdapter(mActivity, listData);
        //瀑布流效果主要是在staggeredAdapter中动态设置每个Item的宽和高来实现的
        headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(staggeredAdapter);

        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);

        View view_header = View.inflate(mActivity,R.layout.layout_header,null);
        View view_footer = View.inflate(mActivity,R.layout.layout_footer,null);

        headerAndFooterRecyclerViewAdapter.addHeaderView(view_header);
        headerAndFooterRecyclerViewAdapter.addFooterView(view_footer);
        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
    }

}
