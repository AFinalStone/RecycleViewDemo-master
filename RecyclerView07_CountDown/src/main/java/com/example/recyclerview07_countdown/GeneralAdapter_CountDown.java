package com.example.recyclerview07_countdown;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 适配器
 * SHI
 * 2016年6月30日 15:36:08
 */
public class GeneralAdapter_CountDown extends RecyclerView.Adapter<GeneralAdapter_CountDown.MyViewHolder> {

    private List<MyBean> mDatas;
    private LayoutInflater mInflater;
    private MyHandler myHandler;
    private long time_current;

    public GeneralAdapter_CountDown(Context context, long timeCurrent, final List<MyBean> mDatas) {
        this.mDatas = mDatas;
        time_current = timeCurrent;
        mInflater = LayoutInflater.from(context);
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessage(myHandler.DATA_USER);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_adapter_general, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MyBean myBean =  mDatas.get(position);
        holder.textView_context.setText(myBean.msg);
        holder.textView_time.setText("当前时间："+TimeUtil.getDateAndTime(time_current));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_context;
        TextView textView_time;

        public MyViewHolder(View view) {
            super(view);
            textView_context = (TextView) view.findViewById(R.id.textView_context);
            textView_time = (TextView) view.findViewById(R.id.textView_time);
        }
    }

    private static class MyHandler extends Handler{

        final private static int DATA_USER = Integer.MIN_VALUE + 1;

        private final WeakReference<GeneralAdapter_CountDown> mAdapter;

        public MyHandler(GeneralAdapter_CountDown sellActivity) {
            mAdapter = new WeakReference(sellActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            GeneralAdapter_CountDown adapter = mAdapter.get();
            if (adapter != null) {
                switch (msg.what) {
                    case DATA_USER:
                        adapter.time_current += 1000;
                        adapter.notifyDataSetChanged();
                        sendEmptyMessageDelayed(DATA_USER,1000);
                        break;
                }
            }
        }
    }
}
