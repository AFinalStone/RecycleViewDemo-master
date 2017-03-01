package com.shi.androidstudy.recycleviewdemo.ui.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.adapter.GeneralAdapter;
import com.shi.androidstudy.recycleviewdemo.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.shi.androidstudy.recycleviewdemo.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 上拉加载下拉刷新
 */
public class HeaderAndFooterRefreshAndLoadRecycleViewFragment_ListView extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private GeneralAdapter generalAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;

    /** 当前刷新事件是否是底部触发的 **/
    private boolean refreshByBottom = false;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_refresh_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //加一是向下滚动,不能继续向下滚动就说明到达底部了
                if (!recyclerView.canScrollVertically(1)) {
                        refreshByBottom = true;
                        openRefreshState();
                }
            }
        });
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
        }
        //设置LinearLayoutManager布局管理器，实现ListView效果
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        generalAdapter = new GeneralAdapter(mActivity,listData);

        headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(generalAdapter);

        View view_header = View.inflate(mActivity,R.layout.layout_header,null);
        View view_footer = View.inflate(mActivity,R.layout.layout_footer,null);
        headerAndFooterRecyclerViewAdapter.addHeaderView(view_header);
        headerAndFooterRecyclerViewAdapter.addFooterView(view_footer);

        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);

    }

    @Override
    public void onRefresh() {
        if (refreshByBottom) {

            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                    Snackbar.make(swipeRefreshLayout,"底部刷新",Snackbar.LENGTH_SHORT).show();
                    refreshByBottom = false;
                }
            },3000);
        } else {
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                    Snackbar.make(swipeRefreshLayout,"顶部刷新",Snackbar.LENGTH_SHORT).show();
                }
            },3000);
        }
    }

    /** 开启刷新状态 **/
    public void openRefreshState() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

}
