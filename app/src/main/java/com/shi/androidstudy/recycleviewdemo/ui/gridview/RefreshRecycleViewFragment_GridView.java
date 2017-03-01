package com.shi.androidstudy.recycleviewdemo.ui.gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.adapter.GeneralAdapter;
import com.shi.androidstudy.recycleviewdemo.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 下拉刷新
 */
public class RefreshRecycleViewFragment_GridView extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private GeneralAdapter generalAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_refresh_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();

        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
        }

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));

        generalAdapter = new GeneralAdapter(mActivity,listData);

        mRecyclerView.setAdapter(generalAdapter);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                Snackbar.make(swipeRefreshLayout,"顶部刷新",Snackbar.LENGTH_SHORT).show();
            }
        },3000);
    }
}
