package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerview_multimodule.R;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class MultiRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final int TYPE_VIEW_01 = Integer.MIN_VALUE + 1;
    private static final int TYPE_VIEW_02 = Integer.MIN_VALUE + 2;
    private static final int TYPE_VIEW_03 = Integer.MIN_VALUE + 3;
    private static final int TYPE_VIEW_04 = Integer.MIN_VALUE + 4;

    private List<String> listData_TYPE_VIEW_01;
    private List<String> listData_TYPE_VIEW_02;
    private List<String> listData_TYPE_VIEW_03;
    private List<String> listData_TYPE_VIEW_04;
    private List<String> listData;

    private LayoutInflater mInflater;

    public MultiRecyclerViewAdapter(Context context, List<String> list) {
        mInflater = LayoutInflater.from(context);
        listData = list;
    }

    public void setListData_TYPE_VIEW_02(List<String> listData_TYPE_VIEW_02) {
        this.listData_TYPE_VIEW_02 = listData_TYPE_VIEW_02;
    }

    public List<String> getListData_TYPE_VIEW_03() {
        return listData_TYPE_VIEW_03;
    }

    public void setListData_TYPE_VIEW_03(List<String> listData_TYPE_VIEW_03) {
        this.listData_TYPE_VIEW_03 = listData_TYPE_VIEW_03;
    }

    public List<String> getListData_TYPE_VIEW_01() {
        return listData_TYPE_VIEW_01;
    }

    public void setListData_TYPE_VIEW_01(List<String> listData_TYPE_VIEW_01) {
        this.listData_TYPE_VIEW_01 = listData_TYPE_VIEW_01;
    }

    public List<String> getListData_TYPE_VIEW_02() {
        return listData_TYPE_VIEW_02;
    }

    public List<String> getListData_TYPE_VIEW_04() {
        return listData_TYPE_VIEW_04;
    }

    public void setListData_TYPE_VIEW_04(List<String> listData_TYPE_VIEW_04) {
        this.listData_TYPE_VIEW_04 = listData_TYPE_VIEW_04;
    }

    @Override
    public int getItemViewType(int position) {
        //记录当前类型数据的起点
        int flagStart = 0;
        //记录当前类型数据的结尾
        int flagEnd = 0;
        if(listData_TYPE_VIEW_01 != null){
            flagEnd = listData_TYPE_VIEW_01.size();
        }
        if(position >= flagStart && position < flagEnd){
            return TYPE_VIEW_01;
        }
        flagStart = flagEnd;
        if(listData_TYPE_VIEW_02 != null){
            flagEnd = flagEnd+listData_TYPE_VIEW_02.size();
        }
        if(position >= flagStart && position < flagEnd){
            return TYPE_VIEW_02;
        }
        flagStart = flagEnd;
        if(listData_TYPE_VIEW_03 != null){
            flagEnd = flagEnd+listData_TYPE_VIEW_03.size();
        }
        if(position >= flagStart && position < flagEnd){
            return TYPE_VIEW_03;
        }
        flagStart = flagEnd;
        if(listData_TYPE_VIEW_04 != null){
//            flagEnd = flagEnd+listData_TYPE_VIEW_04.size();
            flagEnd = flagEnd+1;
        }
        if(position >= flagStart && position < flagEnd){
            return TYPE_VIEW_04;
        }

        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    //我们在 mRecyclerView.setLayoutManager(new GridLayoutManager(this, 6, GridLayoutManager.VERTICAL, false))中
                    //设置条目的宽度为6个条目宽度，这样我们在这里发布会6，则当前条目只会占1个方格
                    switch (type) {
                        case TYPE_VIEW_01:
                        case TYPE_VIEW_04:
                            return gridManager.getSpanCount();//每排一个item
                        case TYPE_VIEW_02:
                            return gridManager.getSpanCount() / 2;//每排两个item
                        case TYPE_VIEW_03:
                            return gridManager.getSpanCount() / 3;//每排三个item
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_VIEW_01) {
            View view = mInflater.inflate(R.layout.item_adapter_view_type_01, parent, false);
            RecyclerView.ViewHolder viewHolder = new MyViewTypeHolder_01(view);
            return viewHolder;
        }
        if (viewType == TYPE_VIEW_02) {
            View view = mInflater.inflate(R.layout.item_adapter_view_type_02, parent, false);
            RecyclerView.ViewHolder viewHolder = new MyViewTypeHolder_01(view);
            return viewHolder;
        }
        if (viewType == TYPE_VIEW_03) {
            View view = mInflater.inflate(R.layout.item_adapter_view_type_03, parent, false);
            RecyclerView.ViewHolder viewHolder = new MyViewTypeHolder_01(view);
            return viewHolder;
        }
        if (viewType == TYPE_VIEW_04) {
            View view = mInflater.inflate(R.layout.item_adapter_view_type_04_parent, parent, false);
            RecyclerView.ViewHolder viewHolder = new MyViewTypeHolder_04(view);
            return viewHolder;
        }
        View view = mInflater.inflate(R.layout.item_adapter_common, parent, false);
        RecyclerView.ViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        MyViewTypeHolder_01 MyViewTypeHolder_01;
        MyViewHolder myHolder;
        switch (type) {
            case TYPE_VIEW_01:
            case TYPE_VIEW_02:
            case TYPE_VIEW_03:
                break;
            case TYPE_VIEW_04:
                {
                    MyViewTypeHolder_04 myViewTypeHolder_04 = (MyViewTypeHolder_04) holder;
                    myViewTypeHolder_04.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager
                            (2, StaggeredGridLayoutManager.VERTICAL));
                    myViewTypeHolder_04.mRecyclerView.setAdapter(new GeneralAdapter(mInflater.getContext(),listData_TYPE_VIEW_04));
                }
                break;
            default:
//                myHolder = (MyViewHolder) holder;
//                myHolder.message.setText(listData.get(position-listData_Horizontal.size()-1));
                break;
        }
    }

    @Override
    public int getItemCount() {
        int totalNum = 0;
        if (listData_TYPE_VIEW_01 != null) {
            totalNum += listData_TYPE_VIEW_01.size();
        }
        if (listData_TYPE_VIEW_02 != null) {
            totalNum += listData_TYPE_VIEW_02.size();
        }
        if (listData_TYPE_VIEW_03 != null) {
            totalNum += listData_TYPE_VIEW_03.size();
        }
        if (listData_TYPE_VIEW_04 != null) {
//            totalNum += listData_TYPE_VIEW_04.size();
            totalNum += 1;
        }
        if (listData != null) {
            totalNum += listData.size();
        }
        return totalNum;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView message;

        public MyViewHolder(View view) {
            super(view);
            message = (TextView) view.findViewById(R.id.id_num);
        }
    }

    //View
    public class MyViewTypeHolder_01 extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public MyViewTypeHolder_01(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_desc);
        }
    }

    //View
    public class MyViewTypeHolder_04 extends RecyclerView.ViewHolder {

        public RecyclerView mRecyclerView;

        public MyViewTypeHolder_04(View view) {
            super(view);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        }
    }

}
