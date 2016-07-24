package com.example.custom.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.frag.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RefreshListView extends ListView {
	private int startY = -1;
	private int MeasuredHeight;
	private boolean isloadMore;
	private View headView, footViews;
	// 记录当前状态
	private int currentState;
	// 三种状态
	private static final int STATE_PULL_REFRESH = 0;// 下拉刷新
	private static final int STATE_RELEASE_REFRESH = 1;// 松开刷新
	private static final int STATE_REFRESHING = 2;// 正在刷新
	private	int footViewHeight;
	private TextView tvTitle;
	private TextView tvTime;
	private ImageView ivArrow;
	private ProgressBar pbProgress;
	private RotateAnimation rAnimationup, rAnimationdown;
	private onRefreshDataListener dataListener;

	public RefreshListView(Context context) {
		super(context);
		initHeadView();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeadView();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeadView();
		initFooterView();
	}

	public void initHeadView() {
		headView = View.inflate(getContext(), R.layout.refresh_top_listview,
				null);// 次布局尽量用线性布局 相对布局可能会蹦
		this.addHeaderView(headView);
		tvTitle = (TextView) headView.findViewById(R.id.ls_tv_title);
		tvTime = (TextView) headView.findViewById(R.id.ls_tv_time);
		ivArrow = (ImageView) headView.findViewById(R.id.iv_arr);
		pbProgress = (ProgressBar) headView.findViewById(R.id.pb_progress);
		tvTime.setText(getTime());
		// 获取高度之前 先mesaure
		headView.measure(0, 0);
		MeasuredHeight = headView.getMeasuredHeight();
		System.out.println(MeasuredHeight + "MeasuredHeight----------");
		// 隐藏View 调用 setPading
		headView.setPadding(0, -MeasuredHeight, 0, 0);
		initAnim();
	}

	public void initFooterView() {
		footViews = View
				.inflate(getContext(), R.layout.activity_footview, null);
		this.addFooterView(footViews);
		footViews.measure(0, 0);
		 footViewHeight = footViews.getMeasuredHeight();
		footViews.setPadding(0, -footViewHeight, 0, 0);
		// 实现滑动接口
		this.setOnScrollListener(new mYScrollListener());
	}

	/**
	 * 触摸事件上拉下拉
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getRawY();
			System.out.println(startY + "---------------YSTART");
			break;
		case MotionEvent.ACTION_MOVE:
			// 有的时候不响应startY 为保证此数据正常拿到
			if (startY == -1) {
				startY = (int) ev.getRawY();
			}
			if (currentState == STATE_REFRESHING) {
				break;
			}
			int endY = (int) ev.getRawY();
			System.out.println(endY + "---------------YEND");
			int dy = endY - startY;// 移动偏移量
			System.out.println(dy + "---------------dy");
			if (dy > 0 && getFirstVisiblePosition() == 0) {
				int padding = dy - MeasuredHeight;
				System.out.println(padding + "---------------padding");
				headView.setPadding(0, padding / 3, 0, 0);
				if (padding > 0 && currentState != STATE_RELEASE_REFRESH) {
					currentState = STATE_RELEASE_REFRESH;
					refreshState();
				} else if (padding < 0 && currentState != STATE_PULL_REFRESH) {
					currentState = STATE_PULL_REFRESH;
					refreshState();
				}
				return true;

			}
			break;
		case MotionEvent.ACTION_UP:
			startY = -1;
			if (currentState == STATE_RELEASE_REFRESH) {
				currentState = STATE_REFRESHING;// 正在刷新
				headView.setPadding(0, 0, 0, 0);// 显示
				refreshState();
			} else if (currentState == STATE_PULL_REFRESH) {
				headView.setPadding(0, -MeasuredHeight, 0, 0);// 隐藏
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	private void refreshState() {
		switch (currentState) {
		case STATE_PULL_REFRESH:// 下拉刷新
			pbProgress.setVisibility(View.INVISIBLE);
			tvTitle.setText("下拉刷新");
			ivArrow.setVisibility(View.VISIBLE);
			ivArrow.startAnimation(rAnimationdown);
			break;
		case STATE_RELEASE_REFRESH:// 松开刷新
			tvTitle.setText("松开刷新");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);
			ivArrow.startAnimation(rAnimationup);
			break;
		case STATE_REFRESHING:
			tvTitle.setText("正在刷新...");
			ivArrow.clearAnimation();// 必须先清除动画,才能隐藏
			ivArrow.setVisibility(View.INVISIBLE);
			pbProgress.setVisibility(View.VISIBLE);
			ivArrow.clearAnimation();// 必须先清除动画,才能隐藏
			dataListener.onRefreshData();
			break;
		default:
			break;
		}

	}

	private void initAnim() {
		// 向上箭头
		rAnimationup = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rAnimationup.setDuration(200);
		rAnimationup.setFillAfter(true);
		// 向下箭头
		rAnimationdown = new RotateAnimation(-180, 0,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rAnimationdown.setDuration(200);
		rAnimationdown.setFillAfter(true);// 如果值为true，控件则保持动画结束的状态
	}

	// 获取时间
	public String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	/*
	 * 设置接口的方法
	 */
	public void setOnRefreshListener(onRefreshDataListener dataListener) {
		this.dataListener = dataListener;
	}

	// 刷新接口

	public interface onRefreshDataListener {
		public void onRefreshData();

		public void onLoadMore();

	}

	// 收起下拉控件 此方法 在获取网络数据successed 时调用

	public void onCompleted() {
		if(isloadMore){
			footViews.setPadding(0, -footViewHeight, 0, 0);
		}else{
		currentState = STATE_PULL_REFRESH;
		pbProgress.setVisibility(View.INVISIBLE);
		tvTitle.setText("下拉刷新");
		ivArrow.setVisibility(View.VISIBLE);
		ivArrow.startAnimation(rAnimationdown);
		headView.setPadding(0, -MeasuredHeight, 0, 0);}

	};

	class mYScrollListener implements OnScrollListener {

		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}

		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			// 判断当庭下来 或者是飞速滑行
			if (scrollState == SCROLL_STATE_IDLE
					|| scrollState == SCROLL_STATE_FLING) {
				if (getLastVisiblePosition() == getCount() - 1 && !isloadMore) { // 滑动到最后
					System.out.println("到头了兄弟");
					footViews.setPadding(0, 0, 0, 0);
					setSelection(getCount() - 1);// 设置显示的位置 定位到ITEM 非常有用
													// 不了解把值改成5 看看
					// 通过boolean 值来做个判断刷新结束
					isloadMore = true;
					//在onCompleted 里做刷新结束
					// 监听
					if (dataListener != null) {
						dataListener.onLoadMore();//调用加载接口
					}
				}

			}

		}

	}
}
