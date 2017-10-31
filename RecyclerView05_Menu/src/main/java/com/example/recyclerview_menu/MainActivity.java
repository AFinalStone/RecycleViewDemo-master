package com.example.recyclerview_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    GeneralAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        List<String> listData = new ArrayList<String>();
        listData.add("真正的才智是刚毅的志向");
        listData.add("生活赋予我们一种巨大的和无限高贵的礼品");
        listData.add("这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春");
        listData.add("志向不过是记忆的奴隶，生气勃勃地降生，但却很难成长");
        adapter = new GeneralAdapter(this, listData);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(adapter);
    }
}
