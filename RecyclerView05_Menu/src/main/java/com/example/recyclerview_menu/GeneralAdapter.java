package com.example.recyclerview_menu;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 适配器
 * SHI
 * 2016年6月30日 15:36:08
 */
public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public GeneralAdapter(Context context, List<String> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_adapter_general, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView_context.setText(mDatas.get(position));
        holder.imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(holder.imageView01,"点击收藏",Snackbar.LENGTH_SHORT).show();
            }
        });
        holder.imageView02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(holder.imageView02,"点击复选框",Snackbar.LENGTH_SHORT).show();
            }
        });
        holder.imageView03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(holder.imageView03,"点击搜索",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_context;
        ImageView imageView01;
        ImageView imageView02;
        ImageView imageView03;

        public MyViewHolder(View view) {
            super(view);
            textView_context = (TextView) view.findViewById(R.id.textView_context);
            imageView01 = (ImageView) view.findViewById(R.id.image01);
            imageView02 = (ImageView) view.findViewById(R.id.image02);
            imageView03 = (ImageView) view.findViewById(R.id.image03);
        }
    }
}
