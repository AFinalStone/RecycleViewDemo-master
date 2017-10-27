package com.example.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 上拉和下拉产生刷新动画的父类RecycleView
 * @author SHI
 * 2016-12-21 10:23:13
 */
public abstract class SwipeRefreshBaseRecycleView<T extends RecyclerView> extends
        SwipeRefreshLayout implements OnRefreshListener {

	private T mRecycleView;
	private OnSwipeRefreshViewListener onRefreshScrollViewListener;
	/** 当前刷新事件是否是底部触发的 **/
	private boolean currentPositionTypeIsBottom = false;
	/**是否开启底部刷新功能**/
	private boolean IfOpenBottomRefresh = false;

	public SwipeRefreshBaseRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs);
		initView(context, attrs, defStyle);
	}

	public SwipeRefreshBaseRecycleView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public SwipeRefreshBaseRecycleView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context, AttributeSet attrs, int defStyle) {
		setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
				Color.CYAN, 0xFFFE5D14, Color.MAGENTA);
		mRecycleView =  initItemView(context, attrs,defStyle);
		mRecycleView.setId(NO_ID);
		addView(mRecycleView);
	}

	private void initView(Context context, AttributeSet attrs) {
		setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
				Color.CYAN, 0xFFFE5D14, Color.MAGENTA);
		mRecycleView =  initItemView(context, attrs);
		mRecycleView.setId(NO_ID);
		addView(mRecycleView);
	}

	private void initView(Context context) {
		setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
				Color.CYAN, 0xFFFE5D14, Color.MAGENTA);
		mRecycleView = initItemView(context);
		mRecycleView.setId(NO_ID);
		addView(mRecycleView);
	}
	
	/**把需要上拉，和下拉刷新的控件初始化并添加到SwipeRefreshLayout中**/
	public abstract T initItemView(Context context, AttributeSet attrs, int defStyle);

	/**把需要上拉，和下拉刷新的控件初始化并添加到SwipeRefreshLayout中**/
	public abstract T initItemView(Context context, AttributeSet attrs);
	
	/**把需要上拉，和下拉刷新的控件初始化并添加到SwipeRefreshLayout中**/
	public abstract T initItemView(Context context);

	/**
	 * 设置当前刷新状态监听者  支持上拉加载更多数据下拉刷新
	 *
	 * @param listener
	 */
	public void setOnRefreshListener(OnSwipeRefreshViewListener listener) {
		this.onRefreshScrollViewListener = listener;
		mRecycleView.addOnScrollListener(new RecycleViewVerticalScrollListener());
		setOnRefreshListener(this);
	}

	/** 开启刷新状态 **/
	public void openRefreshState() {
		post(new Runnable() {
			@Override
			public void run() {
				setRefreshing(true);
				onRefresh();
			}
		});
	}

	/** 是否开启底部刷新功能 **/
	public void IfOpenBottomRefresh(boolean ifOpenBottomRefresh) {
		IfOpenBottomRefresh = ifOpenBottomRefresh;
	}

	/** 关闭刷新状态 **/
	public void closeRefreshState() {
		setRefreshing(false);
	}

	/** 获取当前ItemView,方便以后对自己添加近来的界面进行操作 **/
	public T getRecycleView() {
		return mRecycleView;
	}

	/** 获取当前ItemView,方便以后对自己添加近来的界面进行操作 **/
	public T getRecycleView_LinearLayoutManager() {
		mRecycleView.setLayoutManager(new LinearLayoutManager(mRecycleView.getContext()));
		return mRecycleView;
	}

	/** 获取当前ItemView,方便以后对自己添加近来的界面进行操作 **/
	public T getRecycleView_GridLayoutManager(int spanCount) {
		mRecycleView.setLayoutManager(new GridLayoutManager(mRecycleView.getContext(),spanCount));
		return mRecycleView;
	}

	@Override
	public void onRefresh() {
		if (currentPositionTypeIsBottom) {
			if (onRefreshScrollViewListener != null) {
				onRefreshScrollViewListener.onBottomRefreshListener();
			}
			currentPositionTypeIsBottom = false;
		} else {
			if (onRefreshScrollViewListener != null) {
				onRefreshScrollViewListener.onTopRefreshListener();
			}
		}
	}


	public class RecycleViewVerticalScrollListener
			extends RecyclerView.OnScrollListener {

		@Override
		public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
			//加一是向下滚动,不能继续向下滚动就说明到达底部了
			if (!recyclerView.canScrollVertically(1)) {
				if(IfOpenBottomRefresh){
					currentPositionTypeIsBottom = true;
					openRefreshState();
				}
			}
		}
	}

	/**SwipeRefreshView刷新状态监听接口对象
	 * 2016年5月23日 14:27:25
	 * **/
	public interface OnSwipeRefreshViewListener {
		/**顶部触发刷新时调用**/
		public void onTopRefreshListener();
		/**底部触发刷新时调用**/
		public void onBottomRefreshListener();
	}
}
