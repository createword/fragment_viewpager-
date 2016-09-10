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
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * ����ˢ�µ�ListView
 * 
 * @author Kevin
 * 
 */
public class RefreshListView extends ListView
		implements OnScrollListener, android.widget.AdapterView.OnItemClickListener {

	private static final int STATE_PULL_REFRESH = 0;// ����ˢ��
	private static final int STATE_RELEASE_REFRESH = 1;// �ɿ�ˢ��
	private static final int STATE_REFRESHING = 2;// ����ˢ��

	private View mHeaderView;
	private int startY = -1;// ��������y����
	private int mHeaderViewHeight;
	private int mCurrrentState;

	private TextView tvTitle;
	private TextView tvTime;
	private ImageView ivArrow;
	private ProgressBar pbProgress;
	RotateAnimation rAnimationup, rAnimationdown;
	OnRefreshListener mListener;
	private View mFooterView;
	private int mFooterViewHeight;

	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeaderView();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
		initFooterView();
	}

	public RefreshListView(Context context) {
		super(context);
		initHeaderView();
		initFooterView();
	}

	/**
	 * ��ʼ��ͷ����
	 */
	private void initHeaderView() {
		mHeaderView = View.inflate(getContext(), R.layout.refresh_top_listview, null);
		this.addHeaderView(mHeaderView);

		tvTitle = (TextView) mHeaderView.findViewById(R.id.ls_tv_title);
		tvTime = (TextView) mHeaderView.findViewById(R.id.ls_tv_time);
		ivArrow = (ImageView) mHeaderView.findViewById(R.id.iv_arr);
		pbProgress = (ProgressBar) mHeaderView.findViewById(R.id.pb_progress);

		mHeaderView.measure(0, 0);
		mHeaderViewHeight = mHeaderView.getMeasuredHeight();

		mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);// ����ͷ����

		initAnim();
		tvTime.setText("��ǰʱ��:" + getCurrentTime());
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (startY == -1) {// ȷ��startY��Ч
				startY = (int) ev.getRawY();
			}

			if (mCurrrentState == STATE_REFRESHING) {// ����ˢ��ʱ��������
				break;
			}

			int endY = (int) ev.getRawY();
			int dy = endY - startY;// �ƶ�������

			if (dy > 0 && getFirstVisiblePosition() == 0) {// ֻ���������ҵ�ǰ�ǵ�һ��item,����������
				int padding = dy - mHeaderViewHeight;// ����padding
				mHeaderView.setPadding(0, padding, 0, 0);// ���õ�ǰpadding

				if (padding > 0 && mCurrrentState != STATE_RELEASE_REFRESH) {// ״̬��Ϊ�ɿ�ˢ��
					mCurrrentState = STATE_RELEASE_REFRESH;
					refreshState();
				} else if (padding < 0 && mCurrrentState != STATE_PULL_REFRESH) {// ��Ϊ����ˢ��״̬
					mCurrrentState = STATE_PULL_REFRESH;
					refreshState();
				}

				return true;
			}

			break;
		case MotionEvent.ACTION_UP:
			startY = -1;// ����

			if (mCurrrentState == STATE_RELEASE_REFRESH) {
				mCurrrentState = STATE_REFRESHING;// ����ˢ��
				mHeaderView.setPadding(0, 0, 0, 0);// ��ʾ
				refreshState();
			} else if (mCurrrentState == STATE_PULL_REFRESH) {
				mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);// ����
			}

			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * ˢ�������ؼ��Ĳ���
	 */
	private void refreshState() {
		switch (mCurrrentState) {
		case STATE_PULL_REFRESH:
			tvTitle.setText("����ˢ��");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);
			ivArrow.startAnimation(rAnimationup);
			break;
		case STATE_RELEASE_REFRESH:
			tvTitle.setText("�ɿ�ˢ��");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);
			ivArrow.startAnimation(rAnimationdown);
			break;
		case STATE_REFRESHING:
			tvTitle.setText("����ˢ��...");
			ivArrow.clearAnimation();// �������������,��������
			ivArrow.setVisibility(View.INVISIBLE);
			pbProgress.setVisibility(View.VISIBLE);
			if (mListener != null) {
				mListener.onRefresh();
			}

			break;

		default:
			break;
		}
	}

	private void initAnim() {// ���Ƽ�ͷ����
		// ���ϼ�ͷ
		rAnimationup = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rAnimationup.setDuration(200);
		rAnimationup.setFillAfter(true);
		// ���¼�ͷ
		rAnimationdown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rAnimationdown.setDuration(200);
		rAnimationdown.setFillAfter(true);
	}

	public void setOnRefreshListener(OnRefreshListener refreshListener) {
		mListener = refreshListener;
	}

	// ��������
	public interface OnRefreshListener {
		public void onRefresh();

		public void onLoadMore();// ������һҳ

	}

	public void onRefreshComplete(boolean success) {
		if (isLoadingMore) {// ���ظ���
			mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
			isLoadingMore = false;
		} else {
			mCurrrentState = STATE_PULL_REFRESH;
			tvTitle.setText("����ˢ��");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);

			mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);// ����
			if (success) {
				tvTime.setText("��ǰʱ��:" + getCurrentTime());
			}
		}

	}

	public void initFooterView() {
		mFooterView = View.inflate(getContext(), R.layout.refresh_top_listview, null);
		this.addFooterView(mFooterView);
		mFooterView.measure(0, 0);
		mFooterViewHeight = mFooterView.getMeasuredHeight();
		mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
		this.setOnScrollListener(this);
	}

	public String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());

	}

	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

	}

	private boolean isLoadingMore;

	public void onScrollStateChanged(AbsListView view, int scrollState) {

		if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {

			if (getLastVisiblePosition() == getCount() - 1 && !isLoadingMore) {
				mFooterView.setPadding(0, 0, 0, 0);
				System.out.println("������................");
				setSelection(getCount() - 1);
				isLoadingMore = true;
				if (mListener != null) {
					mListener.onLoadMore();
				}
			}
		}
	}

	OnItemClickListener mItemClickListener;

	@Override
	public void setOnItemClickListener(android.widget.AdapterView.OnItemClickListener listener) {
		// TODO Auto-generated method stub
		super.setOnItemClickListener(this);
		mItemClickListener = listener;
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (mItemClickListener != null) {
			mItemClickListener.onItemClick(parent, view, position - getHeaderViewsCount(), id);
		}

	}

}
