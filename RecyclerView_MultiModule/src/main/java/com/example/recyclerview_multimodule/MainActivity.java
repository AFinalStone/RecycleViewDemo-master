package com.example.recyclerview_multimodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.GeneralAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listData;
    private GeneralAdapter generalAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        listData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
        }
        generalAdapter = new GeneralAdapter(this, listData);
        //设置GridLayoutManager布局管理器，实现GridLayoutManager效果
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(generalAdapter);
    }
}
