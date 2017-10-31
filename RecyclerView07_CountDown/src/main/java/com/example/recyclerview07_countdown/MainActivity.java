package com.example.recyclerview07_countdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    GeneralAdapter_CountDown adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        List<MyBean> listData = new ArrayList<MyBean>();
        for(int i=0; i<20; i++){
            listData.add(new MyBean("+++"+i+"+"+i+"+++",""));
        }
        adapter = new GeneralAdapter_CountDown(this, 0,mRecyclerView,listData);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(adapter);
    }
}
