### RecycleView控件的使用(三) 实现上拉加载更多下拉刷新功能

![效果图](https://raw.githubusercontent.com/AFinalStone/RecycleViewDemo-master/master/pic/swiperefresh.gif)

- 短短几行代码实现,RecycylerView的上拉加载下拉刷新功能，对应的java主要代码文件：

```java
public class RefreshAndLoadRecycleViewFragment_ListView extends BaseFragment implements SwipeRefreshRecycleView.OnSwipeRefreshViewListener{

    private List<String> listData;
    private GeneralAdapter generalAdapter;
    private SwipeRefreshRecycleView mSwipeRefreshRecycleView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_recycle_view, null, false);
        mSwipeRefreshRecycleView = (SwipeRefreshRecycleView) view.findViewById(R.id.mSwipeRefreshRecycleView);
        mSwipeRefreshRecycleView.setOnRefreshListener(this);
        //是否启动底部加载数据功能
        mSwipeRefreshRecycleView.IfOpenBottomRefresh(true);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
        }
        //设置LinearLayoutManager布局管理器，实现ListView效果
        generalAdapter = new GeneralAdapter(mActivity, listData);
        mSwipeRefreshRecycleView.getRecycleView_LinearLayoutManager().setAdapter(generalAdapter);

    }

    @Override
    public void onTopRefreshListener() {

        mSwipeRefreshRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshRecycleView.closeRefreshState();
                Snackbar.make(mSwipeRefreshRecycleView, "顶部刷新", Snackbar.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    @Override
    public void onBottomRefreshListener() {

        mSwipeRefreshRecycleView.getRecycleView_LinearLayoutManager().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshRecycleView.closeRefreshState();
                Snackbar.make(mSwipeRefreshRecycleView, "底部刷新", Snackbar.LENGTH_SHORT).show();
            }
        }, 3000);
    }

}

```

- 对应的布局文件:
```java
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <com.example.view.SwipeRefreshRecycleView
        android:id="@+id/mSwipeRefreshRecycleView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</FrameLayout>

```

- 实现上拉加载下拉刷新主要用到一个自定义组合控件SwipeRefreshRecycleView对象

```java
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
```

- SwipeRefreshBaseRecycleView对象:

```java
**
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

```