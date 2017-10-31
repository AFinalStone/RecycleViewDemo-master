package com.example.recyclerview_checkbox;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;

/**
 * 适配器
 * SHI
 * 2016年6月30日 15:36:08
 */
public class GeneralAdapter_MultiCheck extends RecyclerView.Adapter<GeneralAdapter_MultiCheck.MyViewHolder> {

    private List<MyBean> mDatas;
    private LayoutInflater mInflater;
    HashSet<Integer> selectData = new HashSet<>();

    public GeneralAdapter_MultiCheck(Context context, List<MyBean> mDatas) {
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
        final MyBean myBean =  mDatas.get(position);
        holder.textView_context.setText(myBean.msg);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectData.add(position);
                }else{
                    selectData.remove(position);
                }
            }
        });
        if(selectData.contains(position)){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_context;
        CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            textView_context = (TextView) view.findViewById(R.id.textView_context);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }
}
