package com.example.activity;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.example.base.utils.Utils;
import com.example.frag.R;
import com.example.modle.ImageFloderModel;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SwitchPicActivity extends BaseActivity {
	private String firstImage;
	private int mPicsSize;// �洢�ļ����е�ͼƬ����
	private File mImgDir;// ͼƬ���������ļ���
	private View view;
	private TextView allpic;
	private int totle_pic = 0;
	private GridView gridView;
	private RelativeLayout relOnClick;
	private List<String> arrayListPic;
	private ImageFloderModel floderModel;
	private HashSet<String> mHashSet = null;
	private List<ImageFloderModel> arrayModleList;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			setViewAdapter();
		};
	};

	@Override
	public void initView() {
		act_base_title.setText("ͼƬ����");
		right_button.setText("ȷ��");
		right_button.setVisibility(View.VISIBLE);
		view = LayoutInflater.from(this).inflate(R.layout.activity_switchpic,
				null);
		ViewIsNetWorkState(view, 0);
		allpic = (TextView) view.findViewById(R.id.all_pic);
		gridView = (GridView) view.findViewById(R.id.grid_pic);
		relOnClick = (RelativeLayout) view.findViewById(R.id.id_relOnclick);
	}

	@Override
	public void initData() {
		mHashSet = new HashSet<String>();
		getPicParam();
	}

	public void setViewAdapter() {
		if (mImgDir == null) {
			Utils.ToastShort(getBaseContext(), "һ��ͼƬû��ɨ�赽");
			return;
		}
		arrayListPic = Arrays.asList(mImgDir.list()); // �൱��parentFile
	}

	/**
	 * ��ȡͼƬparam
	 */
	public void getPicParam() {

		// ���ж�SD ���Ƿ���� �����˼�Ȩ��
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Utils.ToastShort(SwitchPicActivity.this, "SD��������");
		}
		final Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

		new Thread(new Runnable() {
			public void run() {
				ContentResolver resolver = SwitchPicActivity.this
						.getContentResolver();
				Cursor mCursor = resolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or"
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);
				while (mCursor.moveToNext()) {
					String imageUrl = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));// ��ȡͼƬ·��
					// �õ���һ��ͼƬ��·��
					if (firstImage == null)
						firstImage = imageUrl;
					// ��ȡ��ͼƬ�ĸ�·���ļ�
					File parentFiel = new File(imageUrl).getParentFile();
					if (parentFiel == null)
						continue;
					// ��ȡ��·��
					String dirPath = parentFiel.getAbsolutePath();
					// ����HashSet ����ͼƬ
					if (mHashSet.contains(dirPath)) {
						continue;
					} else {
						mHashSet.add(dirPath);
						floderModel = new ImageFloderModel();
						floderModel.setDir(dirPath);
						
						floderModel.setFirstImagePath(imageUrl);
					}
					// ��ȡ�ļ���ͼƬ�ĸ���
					int picCount = parentFiel.list(new FilenameFilter() {

						public boolean accept(File dir, String filename) {
							// ��ѡָ��������
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;
					// ��ȡ������ һ��һ�����
					totle_pic += picCount;
					floderModel.setCount(picCount);
					arrayModleList.add(floderModel);
					if (picCount > mPicsSize) {
						mPicsSize = picCount;
						mImgDir = parentFiel;
					}
				}
				mCursor.close();

				// ɨ����ɣ�������HashSetҲ�Ϳ����ͷ��ڴ���
				mHashSet = null;

				// ֪ͨHandlerɨ��ͼƬ���
				mHandler.sendEmptyMessage(0x110);
			}
		}).start();
	}

}
