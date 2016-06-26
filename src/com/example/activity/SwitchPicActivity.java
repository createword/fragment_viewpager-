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
	private int mPicsSize;// 存储文件夹中的图片数量
	private File mImgDir;// 图片数量最多的文件夹
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
		act_base_title.setText("图片加载");
		right_button.setText("确定");
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
			Utils.ToastShort(getBaseContext(), "一张图片没有扫描到");
			return;
		}
		arrayListPic = Arrays.asList(mImgDir.list()); // 相当于parentFile
	}

	/**
	 * 获取图片param
	 */
	public void getPicParam() {

		// 先判断SD 卡是否存在 别忘了加权限
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Utils.ToastShort(SwitchPicActivity.this, "SD卡不存在");
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
							.getColumnIndex(MediaStore.Images.Media.DATA));// 获取图片路径
					// 拿到第一张图片的路径
					if (firstImage == null)
						firstImage = imageUrl;
					// 获取该图片的父路径文件
					File parentFiel = new File(imageUrl).getParentFile();
					if (parentFiel == null)
						continue;
					// 获取父路径
					String dirPath = parentFiel.getAbsolutePath();
					// 利用HashSet 过滤图片
					if (mHashSet.contains(dirPath)) {
						continue;
					} else {
						mHashSet.add(dirPath);
						floderModel = new ImageFloderModel();
						floderModel.setDir(dirPath);
						
						floderModel.setFirstImagePath(imageUrl);
					}
					// 获取文件夹图片的个数
					int picCount = parentFiel.list(new FilenameFilter() {

						public boolean accept(File dir, String filename) {
							// 节选指定的类型
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;
					// 获取总张数 一张一张添加
					totle_pic += picCount;
					floderModel.setCount(picCount);
					arrayModleList.add(floderModel);
					if (picCount > mPicsSize) {
						mPicsSize = picCount;
						mImgDir = parentFiel;
					}
				}
				mCursor.close();

				// 扫描完成，辅助的HashSet也就可以释放内存了
				mHashSet = null;

				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(0x110);
			}
		}).start();
	}

}
