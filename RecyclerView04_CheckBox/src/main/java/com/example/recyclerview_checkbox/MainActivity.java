package com.example.recyclerview_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewLeftMulti;
    private RecyclerView mRecyclerViewRightSingle;
    GeneralAdapter_MultiCheck multiAdapter;
    GeneralAdapter_SingleCheck singleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerViewLeftMulti = (RecyclerView) findViewById(R.id.mRecyclerViewLeftMulti);
        mRecyclerViewRightSingle = (RecyclerView) findViewById(R.id.mRecyclerViewRightSingle);
        initMultiCheckRecyclerView();
        initSingleCheckRecyclerView();
    }

    private void initMultiCheckRecyclerView(){
        List<MyBean> listData = new ArrayList<MyBean>();
        for(int i=0; i<20; i++){
            listData.add(new MyBean("+++"+i+"+"+i+"+++"));
        }
        multiAdapter = new GeneralAdapter_MultiCheck(this, listData);
        mRecyclerViewLeftMulti.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerViewLeftMulti.setAdapter(multiAdapter);
    }

    private void initSingleCheckRecyclerView(){
        List<MyBean> listData = new ArrayList<MyBean>();
        for(int i=0; i<20; i++){
            listData.add(new MyBean("+++"+i+"+"+i+"+++"));
        }
        singleAdapter = new GeneralAdapter_SingleCheck(this, listData);
        mRecyclerViewRightSingle.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerViewRightSingle.setAdapter(singleAdapter);
    }

}
