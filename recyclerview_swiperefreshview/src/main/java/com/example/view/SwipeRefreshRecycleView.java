package com.example.view;




import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 上拉和下拉产生刷新动画的RecycleView
 * @author SHI
 * 2016-12-21 10:22:56
 */  
public class SwipeRefreshRecycleView extends SwipeRefreshBaseRecycleView<RecyclerView>{


	public SwipeRefreshRecycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwipeRefreshRecycleView(Context context) {
		super(context);
	}

	@Override
	public RecyclerView initItemView(Context context, AttributeSet attrs, int defStyle) {
		return new RecyclerView(context,attrs,defStyle);
	}

	@Override
	public RecyclerView initItemView(Context context, AttributeSet attrs) {
		return new RecyclerView(context,attrs);
	}

	@Override
	public RecyclerView initItemView(Context context) {
		return new RecyclerView(context);
	}

}
