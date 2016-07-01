package com.shi.androidstudy.recycleviewdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 模仿listView
 */
public class ListViewRecycleViewFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private CommonAdapter commonAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //初始化数据集
        listData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
            listData.add("" + (char) i);
        }

        //设置默认动画，添加addData()或者removeData()时候的动画
         mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置LinearLayoutManager布局管理器，实现ListView效果
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //创建适配器
        commonAdapter = new CommonAdapter(mActivity,listData);
        //设置设配器
        mRecyclerView.setAdapter(commonAdapter);
        //添加水平分割线,想要改变水平分割线的风格可以在主题中通过改变listDivider来设置
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));
        //设置item条目点击监听事件
        commonAdapter.setOnItemClickLitener(new CommonAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //添加一个新的条目，并使用之前设置的动画效果
                commonAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //删除一个条目，并使用之前设置的动画效果
                commonAdapter.removeData(position);
            }
        });
    }

}
