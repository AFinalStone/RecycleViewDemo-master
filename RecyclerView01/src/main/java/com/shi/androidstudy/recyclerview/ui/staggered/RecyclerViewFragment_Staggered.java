package com.shi.androidstudy.recyclerview.ui.staggered;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shi.androidstudy.recyclerview.R;
import com.shi.androidstudy.recyclerview.adapter.StaggeredAdapter;
import com.shi.androidstudy.recyclerview.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *  瀑布流效果
 * @author shi
 * @time 2016/6/30 16:54
 */
public class RecyclerViewFragment_Staggered extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private StaggeredAdapter staggeredAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_simple_recycle_view, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        listData = new ArrayList<String>();

        for (int i = 0; i < 20; i++)

        {
            listData.add("" + i);
        }

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //StaggeredGridLayoutManager，实现瀑布流效果,每行展示3个item
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));

        staggeredAdapter = new StaggeredAdapter(mActivity, listData);
        //瀑布流效果主要是在staggeredAdapter中动态设置每个Item的宽和高来实现的
        mRecyclerView.setAdapter(staggeredAdapter);
        staggeredAdapter.setOnItemClickLitener(new StaggeredAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                staggeredAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                staggeredAdapter.removeData(position);
            }
        });
    }

}
