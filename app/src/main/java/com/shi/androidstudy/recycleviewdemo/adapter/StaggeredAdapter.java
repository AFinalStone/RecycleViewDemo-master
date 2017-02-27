package com.shi.androidstudy.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.shi.androidstudy.recycleviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流适配器
 * @author SHI
 * @time 2016/7/footer 11:40
 */
public class StaggeredAdapter extends	RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>
{

	private List<String> mDatas;
	private LayoutInflater mInflater;

	private List<Integer> mHeights;
	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);

		void onItemLongClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	public StaggeredAdapter(Context context, List<String> datas)
	{
		mInflater = LayoutInflater.from(context);
		mDatas = datas;

		mHeights = new ArrayList<Integer>();
		for (int i = 0; i < mDatas.size(); i++)
		{
			mHeights.add( (int) (100 + Math.random() * 300));
		}
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.item_staggered_adapter, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position)
	{
		//动态设置Item的宽和高，实现瀑布流效果
		LayoutParams lp = holder.tv.getLayoutParams();
		lp.height = mHeights.get(position);

		holder.tv.setLayoutParams(lp);
		holder.tv.setText(mDatas.get(position));

		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null)
		{
			holder.itemView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});

			holder.itemView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					return false;
				}
			});
		}
	}


	@Override
	public int getItemCount()
	{
		return mDatas.size();
	}

	public void addData(int position, String str)
	{
		mDatas.add(position, str);
		mHeights.add( (int) (100 + Math.random() * 300));
		notifyItemInserted(position);
	}

	public void removeData(int position)
	{
		if(position < mDatas.size()) {
			mDatas.remove(position);
			notifyItemRemoved(position);
		}
	}

	public class MyViewHolder extends ViewHolder
	{

		TextView tv;

		public MyViewHolder(View view)
		{
			super(view);
			tv = (TextView) view.findViewById(R.id.id_num);

		}
	}
}