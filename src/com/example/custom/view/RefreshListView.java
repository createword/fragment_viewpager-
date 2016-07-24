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
	// ��¼��ǰ״̬
	private int currentState;
	// ����״̬
	private static final int STATE_PULL_REFRESH = 0;// ����ˢ��
	private static final int STATE_RELEASE_REFRESH = 1;// �ɿ�ˢ��
	private static final int STATE_REFRESHING = 2;// ����ˢ��
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
				null);// �β��־��������Բ��� ��Բ��ֿ��ܻ��
		this.addHeaderView(headView);
		tvTitle = (TextView) headView.findViewById(R.id.ls_tv_title);
		tvTime = (TextView) headView.findViewById(R.id.ls_tv_time);
		ivArrow = (ImageView) headView.findViewById(R.id.iv_arr);
		pbProgress = (ProgressBar) headView.findViewById(R.id.pb_progress);
		tvTime.setText(getTime());
		// ��ȡ�߶�֮ǰ ��mesaure
		headView.measure(0, 0);
		MeasuredHeight = headView.getMeasuredHeight();
		System.out.println(MeasuredHeight + "MeasuredHeight----------");
		// ����View ���� setPading
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
		// ʵ�ֻ����ӿ�
		this.setOnScrollListener(new mYScrollListener());
	}

	/**
	 * �����¼���������
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getRawY();
			System.out.println(startY + "---------------YSTART");
			break;
		case MotionEvent.ACTION_MOVE:
			// �е�ʱ����ӦstartY Ϊ��֤�����������õ�
			if (startY == -1) {
				startY = (int) ev.getRawY();
			}
			if (currentState == STATE_REFRESHING) {
				break;
			}
			int endY = (int) ev.getRawY();
			System.out.println(endY + "---------------YEND");
			int dy = endY - startY;// �ƶ�ƫ����
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
				currentState = STATE_REFRESHING;// ����ˢ��
				headView.setPadding(0, 0, 0, 0);// ��ʾ
				refreshState();
			} else if (currentState == STATE_PULL_REFRESH) {
				headView.setPadding(0, -MeasuredHeight, 0, 0);// ����
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	private void refreshState() {
		switch (currentState) {
		case STATE_PULL_REFRESH:// ����ˢ��
			pbProgress.setVisibility(View.INVISIBLE);
			tvTitle.setText("����ˢ��");
			ivArrow.setVisibility(View.VISIBLE);
			ivArrow.startAnimation(rAnimationdown);
			break;
		case STATE_RELEASE_REFRESH:// �ɿ�ˢ��
			tvTitle.setText("�ɿ�ˢ��");
			ivArrow.setVisibility(View.VISIBLE);
			pbProgress.setVisibility(View.INVISIBLE);
			ivArrow.startAnimation(rAnimationup);
			break;
		case STATE_REFRESHING:
			tvTitle.setText("����ˢ��...");
			ivArrow.clearAnimation();// �������������,��������
			ivArrow.setVisibility(View.INVISIBLE);
			pbProgress.setVisibility(View.VISIBLE);
			ivArrow.clearAnimation();// �������������,��������
			dataListener.onRefreshData();
			break;
		default:
			break;
		}

	}

	private void initAnim() {
		// ���ϼ�ͷ
		rAnimationup = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rAnimationup.setDuration(200);
		rAnimationup.setFillAfter(true);
		// ���¼�ͷ
		rAnimationdown = new RotateAnimation(-180, 0,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rAnimationdown.setDuration(200);
		rAnimationdown.setFillAfter(true);// ���ֵΪtrue���ؼ��򱣳ֶ���������״̬
	}

	// ��ȡʱ��
	public String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	/*
	 * ���ýӿڵķ���
	 */
	public void setOnRefreshListener(onRefreshDataListener dataListener) {
		this.dataListener = dataListener;
	}

	// ˢ�½ӿ�

	public interface onRefreshDataListener {
		public void onRefreshData();

		public void onLoadMore();

	}

	// ���������ؼ� �˷��� �ڻ�ȡ��������successed ʱ����

	public void onCompleted() {
		if(isloadMore){
			footViews.setPadding(0, -footViewHeight, 0, 0);
		}else{
		currentState = STATE_PULL_REFRESH;
		pbProgress.setVisibility(View.INVISIBLE);
		tvTitle.setText("����ˢ��");
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
			// �жϵ�ͥ���� �����Ƿ��ٻ���
			if (scrollState == SCROLL_STATE_IDLE
					|| scrollState == SCROLL_STATE_FLING) {
				if (getLastVisiblePosition() == getCount() - 1 && !isloadMore) { // ���������
					System.out.println("��ͷ���ֵ�");
					footViews.setPadding(0, 0, 0, 0);
					setSelection(getCount() - 1);// ������ʾ��λ�� ��λ��ITEM �ǳ�����
													// ���˽��ֵ�ĳ�5 ����
					// ͨ��boolean ֵ�������ж�ˢ�½���
					isloadMore = true;
					//��onCompleted ����ˢ�½���
					// ����
					if (dataListener != null) {
						dataListener.onLoadMore();//���ü��ؽӿ�
					}
				}

			}

		}

	}
}
