package com.shi.androidstudy.recycleviewdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shi.androidstudy.recycleviewdemo.R;
import com.shi.androidstudy.recycleviewdemo.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHI on 2016/6/23.
 * 模仿水平GridView
 */
public class HorizontalGridViewRecycleViewFragment extends BaseFragment {

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
        listData = new ArrayList<String>();

        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
            listData.add("" + (char) i);
        }

        //StaggeredGridLayoutManager，实现水平GridView效果,每列展示四个item
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.HORIZONTAL));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        commonAdapter = new CommonAdapter(mActivity,listData);

        mRecyclerView.setAdapter(commonAdapter);

        commonAdapter.setOnItemClickLitener(new CommonAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                commonAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                commonAdapter.removeData(position);
            }
        });
    }

}
