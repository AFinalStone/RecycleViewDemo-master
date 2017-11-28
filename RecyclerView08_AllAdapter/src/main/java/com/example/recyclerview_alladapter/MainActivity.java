package com.example.recyclerview_alladapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    AllCommonAdapter<MyBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        List<MyBean> listData = new ArrayList();
        listData.add(new MyBean(R.mipmap.image01, "条目___11111"));
        listData.add(new MyBean(R.mipmap.image02, "条目___22222"));
        listData.add(new MyBean(R.mipmap.image03, "条目___33333"));
        listData.add(new MyBean(R.mipmap.image04, "条目___44444"));
        adapter = new AllCommonAdapter<MyBean>(this, R.layout.item_adapter_general, listData) {
            @Override
            public void convert(ViewHolder holder, MyBean myBean) {
                holder.setImageResource(R.id.imageView, myBean.getImageResID());
                holder.setText(R.id.textView_context, myBean.getContext());
            }
        };

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setAdapter(adapter);
    }
}
