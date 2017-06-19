package com.example.headerandfooter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adapter.GeneralAdapter;
import com.example.adapter.HeaderAndFooterRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 带头部和尾部的控件
 */
public class HeaderAndFooterRecycleViewFragment_ListView extends BaseFragment{

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private GeneralAdapter generalAdapter;
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
        //设置LinearLayoutManager布局管理器，实现ListView效果
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        //初始化适配器，适配器headerAndFooterRecyclerViewAdapter包含generalAdapter,
        //在适配器headerAndFooterRecyclerViewAdapter中包含generalAdapter，
        //然后在适配器headerAndFooterRecyclerViewAdapter中进行适配器的选择性使用
        generalAdapter = new GeneralAdapter(mActivity,listData);
        headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(generalAdapter);

        ImageView view_header = (ImageView) LayoutInflater.from(mActivity).inflate(R.layout.layout_header,null, false);
        view_header.setImageResource(R.mipmap.header02);
        View view_footer = View.inflate(mActivity,R.layout.layout_footer,null);

        headerAndFooterRecyclerViewAdapter.addHeaderView(view_header);
        headerAndFooterRecyclerViewAdapter.addFooterView(view_footer);
        mRecyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
    }

}
