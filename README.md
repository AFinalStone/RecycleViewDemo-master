### RecycleView控件的使用(一) ListView,GridView,水平GridView,瀑布流效果实现

RecycleView控件出来也有很久一段时间了，本文简单讲说一下RecycleView控件的使用方法。我们不打算讲那些特别专业的概念性的术语和原理，比如什么是RecycleView了，RecycleView的底层实现，RecycleView的动画效果实现过程之类的东西。

我们直接上代码，把想要的效果成功实现出来，我们先体验一下整个流程，等我们用很简单的代码实现了自己想要的效果的时候，再回头看代码，肯定会简单明了。

文章前面用到的一些资源文件和适配器对象，为了让文章看起来更整洁，统一在文章最后面给出。

**一、ListView效果：**

![这里写图片描述](http://img.blog.csdn.net/20160701105506553)

- **具体代码实现**：
```java
/**
 * Created by SHI on 2016/6/23.
 * 模仿listView
 */
public class ListViewRecycleViewFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private CommonAdapter commonAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //初始化数据集
        listData = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
            listData.add("" + (char) i);
        }

        //设置默认动画，添加addData()或者removeData()时候的动画
         mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置LinearLayoutManager布局管理器，实现ListView效果
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //创建适配器
        commonAdapter = new CommonAdapter(mActivity,listData);
        //设置设配器
        mRecyclerView.setAdapter(commonAdapter);
        //添加水平分割线,想要改变水平分割线的风格可以在主题中通过改变listDivider来设置
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));
        //设置item条目点击监听事件
        commonAdapter.setOnItemClickLitener(new CommonAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //添加一个新的条目，并使用之前设置的动画效果
                commonAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //删除一个条目，并使用之前设置的动画效果
                commonAdapter.removeData(position);
            }
        });
    }

}
```
**二、GridView效果：**

![这里写图片描述](http://img.blog.csdn.net/20160701110647867)

- **具体代码实现**：
```java
/**
 * Created by SHI on 2016/6/23.
 * 模仿GridView
 */
public class GridViewRecycleViewFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private CommonAdapter commonAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();

        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
            listData.add("" + (char) i);
        }

        //设置GridLayoutManager布局管理器，实现GridView效果,每行展示四个item
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity,4));
        //添加水平分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));
        //添加竖直分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.HORIZONTAL_LIST));
        //设置默认动画，添加addData()或者removeData()时候的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        commonAdapter = new CommonAdapter(mActivity,listData);
        mRecyclerView.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickLitener(new CommonAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                commonAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                commonAdapter.removeData(position);
            }
        });
    }

}
```
**三、水平GridView效果：**

![这里写图片描述](http://img.blog.csdn.net/20160701113735674)

- **具体代码实现**：
```java
/**
 * Created by SHI on 2016/6/23.
 * 模仿水平GridView
 */
public class HorizontalGridViewRecycleViewFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private CommonAdapter commonAdapter;


    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view,null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        listData = new ArrayList<String>();

        for (int i = 'A'; i < 'z'; i++) {
            listData.add("" + (char) i);
            listData.add("" + (char) i);
        }

        //StaggeredGridLayoutManager，实现水平GridView效果,每列展示四个item
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.HORIZONTAL));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.HORIZONTAL_LIST));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        commonAdapter = new CommonAdapter(mActivity,listData);

        mRecyclerView.setAdapter(commonAdapter);

        commonAdapter.setOnItemClickLitener(new CommonAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                commonAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                commonAdapter.removeData(position);
            }
        });
    }

}
```



**四、瀑布流效果：**

![这里写图片描述](http://img.blog.csdn.net/20160701111608598)

- **具体代码实现**：
```java
/**
 *  瀑布流效果
 * @author shi
 * @time 2016/6/30 16:54
 */
public class StaggeredRecycleFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private List<String> listData;
    private StaggeredAdapter staggeredAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recycle_view, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        listData = new ArrayList<String>();

        for (int i = 0; i < 20; i++)

        {
            listData.add("" + i);
        }

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //StaggeredGridLayoutManager，实现瀑布流效果,每行展示3个item
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));

        staggeredAdapter = new StaggeredAdapter(mActivity, listData);
        //瀑布流效果主要是在staggeredAdapter中动态设置每个Item的宽和高来实现的
        mRecyclerView.setAdapter(staggeredAdapter);
        staggeredAdapter.setOnItemClickLitener(new StaggeredAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                staggeredAdapter.addData(position,"insert new Data");
            }

            @Override
            public void onItemLongClick(View view, int position) {
                staggeredAdapter.removeData(position);
            }
        });
    }

}
```
**五、文章用到的一些比较重要的对象和资源文件**

- **CommonAdapter适配器**：
```java
/**
 * 适配器
 * SHI
 * 2016年6月30日 15:36:08
 */
class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public interface OnItemClickLitener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public CommonAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_common_adapter, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position,String str) {
        mDatas.add(position, str);
        notifyItemInserted(position);
    }


    public void removeData(int position) {
        if (position < mDatas.size()) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

   public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);


        }
    }
}
```
- **StaggeredAdapter 适配器**
```java
/**
 * 瀑布流适配器
 * @author SHI
 * @time 2016/7/1 11:40
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
        if (position < mDatas.size()) {
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
```

- **分割线效果的实现**：
```java
/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 * 实现分割线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        Log.v("recyclerview", "onDraw()");

        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }


    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
```

- **fragment_recycle_view.xml布局文件**：
```java
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    tools:context=".ui.recycleview.ListViewRecycleViewFragment">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/id_recyclerview"
        android:divider="#ffff0000"
        android:dividerHeight="2dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</FrameLayout>
```
- **item_common_adapter.xml布局文件**：
```java
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:background="@drawable/item_bg"
    android:layout_height="wrap_content"
    >

    <TextView
        android:id="@+id/id_num"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:gravity="center"
        android:layout_gravity="center"
        android:text="1" />

</FrameLayout>
```
- **item_staggered_adapter.xml布局文件**：
```java
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:background="@drawable/item_bg"
    android:layout_margin="5dp"
    android:layout_height="wrap_content"
    >

    <TextView
        android:id="@+id/id_num"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:gravity="center"
        android:layout_gravity="center"
        android:text="1" />

</FrameLayout>
```

我们先按照代码把效果实现出来，再具体研究代码，相信很快就能得心应手的使用RecycleView控件了。
最后附上demo下载地址：[打开链接](https://github.com/AFinalStone/RecycleViewDemo-master/tree/master)

### RecycleView控件的使用(二) 为RecyclerView添加头文件和尾部文件


和ListView添加头布局(addHeaderView)和尾部局(addFooterView)的方式不同，RecyclerView本身并没有提供
这样的功能方法，我们需要自定义适配器，使得适配器内部包含三个适配器：头部适配器，尾部适配器，
以及条目适配器。

！[效果图](/pic/header_footer.gif)



