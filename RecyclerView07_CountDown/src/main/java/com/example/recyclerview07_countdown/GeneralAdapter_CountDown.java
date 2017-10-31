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
    private int firstVisiblePosition = 0;
    private int lastVisiblePosition = 6;
    private long time_current;

    public GeneralAdapter_CountDown(Context context, long timeCurrent, RecyclerView recyclerView, final List<MyBean> mDatas) {
        this.mDatas = mDatas;
        time_current = timeCurrent;
        mInflater = LayoutInflater.from(context);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastVisiblePosition = linearManager.findLastVisibleItemPosition();
                    if(lastVisiblePosition < mDatas.size()-1){
                        lastVisiblePosition += lastVisiblePosition;
                    }
                    //获取第一个可见view的位置
                    firstVisiblePosition = linearManager.findFirstVisibleItemPosition();
                    if(firstVisiblePosition >= 1){
                        firstVisiblePosition -= firstVisiblePosition;
                    }
                }
            }
        });
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
        final MyBean myBean =  mDatas.get(position);
        holder.textView_context.setText(myBean.msg);
        holder.textView_time.setText("当前时间："+time_current/1000);
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
            mAdapter = new WeakReference<GeneralAdapter_CountDown>(sellActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            GeneralAdapter_CountDown adapter = mAdapter.get();
            if (adapter != null) {
                switch (msg.what) {
                    case DATA_USER:
                        adapter.time_current += 1000;
                        for(int i=adapter.firstVisiblePosition; i<adapter.lastVisiblePosition; i++){
                            adapter.notifyItemChanged(i);
                        }
                        sendEmptyMessageDelayed(DATA_USER,1000);
                        break;
                }
            }
        }
    }
}
