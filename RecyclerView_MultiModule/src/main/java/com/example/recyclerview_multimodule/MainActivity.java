package com.example.recyclerview_multimodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.GeneralAdapter;
import com.example.adapter.MultiRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MultiRecyclerViewAdapter mMultiRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        List<String> listData = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            listData.add("");
        }
        List<String> listData01 = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            listData01.add("");
        }
        List<String> listData02 = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            listData02.add("");
        }
        List<String> listData03 = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            listData03.add("");
        }
        List<String> listData04 = new ArrayList<String>();
        for (int i = 0; i < 49; i++) {
            listData04.add("listData04:"+i);
        }

        mMultiRecyclerViewAdapter = new MultiRecyclerViewAdapter(this,listData);
        mMultiRecyclerViewAdapter.setListData_TYPE_VIEW_01(listData01);
        mMultiRecyclerViewAdapter.setListData_TYPE_VIEW_02(listData02);
        mMultiRecyclerViewAdapter.setListData_TYPE_VIEW_03(listData03);
        mMultiRecyclerViewAdapter.setListData_TYPE_VIEW_04(listData04);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMultiRecyclerViewAdapter);
    }
}
