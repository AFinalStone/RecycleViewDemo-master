package com.example.recyclerview_swiperefreshview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.GeneralAdapter;
import com.example.view.SwipeRefreshRecycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 上拉加载下拉刷新
 */
public class RefreshAndLoadRecycleViewFragment_GridView extends BaseFragment implements SwipeRefreshRecycleView.OnSwipeRefreshViewListener{

    private List<String> listData;
    private GeneralAdapter generalAdapter;
    private SwipeRefreshRecycleView mSwipeRefreshRecycleView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_recycle_view, null, false);
        mSwipeRefreshRecycleView = (SwipeRefreshRecycleView) view.findViewById(R.id.mSwipeRefreshRecycleView);
        mSwipeRefreshRecycleView.setOnRefreshListener(this);
        //是否启动底部加载数据功能
        mSwipeRefreshRecycleView.IfOpenBottomRefresh(true);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
        }
        //设置LinearLayoutManager布局管理器，实现ListView效果
        generalAdapter = new GeneralAdapter(mActivity, listData);
        mSwipeRefreshRecycleView.getRecycleView_GridLayoutManager(3).setAdapter(generalAdapter);

    }

    @Override
    public void onTopRefreshListener() {

        mSwipeRefreshRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshRecycleView.closeRefreshState();
                Snackbar.make(mSwipeRefreshRecycleView, "顶部刷新", Snackbar.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    @Override
    public void onBottomRefreshListener() {

        mSwipeRefreshRecycleView.getRecycleView_LinearLayoutManager().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshRecycleView.closeRefreshState();
                Snackbar.make(mSwipeRefreshRecycleView, "底部刷新", Snackbar.LENGTH_SHORT).show();
            }
        }, 3000);
    }

}
