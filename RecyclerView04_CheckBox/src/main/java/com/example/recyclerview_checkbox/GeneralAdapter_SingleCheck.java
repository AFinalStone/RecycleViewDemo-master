package com.example.recyclerview_checkbox;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * 适配器
 * SHI
 * 2016年6月30日 15:36:08
 */
public class GeneralAdapter_SingleCheck extends RecyclerView.Adapter<GeneralAdapter_SingleCheck.MyViewHolder> {

    private List<MyBean> mDatas;
    private LayoutInflater mInflater;
    private int oldCheckPosition = -1;
    public GeneralAdapter_SingleCheck(Context context, List<MyBean> mDatas) {
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
                    clearOtherCheckBox(position);
                }
            }
        });
        if(oldCheckPosition == position){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
    }

    private void clearOtherCheckBox(final int currentPosition){
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                //不能直接在onBindViewHolder方法中调用刷新的方法，否则会报异常
                if(oldCheckPosition != -1){
                    notifyItemChanged(oldCheckPosition);
                }
                oldCheckPosition = currentPosition;
            }
        };
        handler.post(r);

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
