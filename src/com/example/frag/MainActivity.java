package com.example.frag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;


public class MainActivity extends FragmentActivity {

	private static final String FRAGMENT_CONTENT = "fragment_content";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initFragment();
	}

	
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
	transaction.replace(R.id.fl_content, new ContentFragment(),
				FRAGMENT_CONTENT);

		transaction.commit();// 鎻愪氦浜嬪姟
		// Fragment leftMenuFragment = fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
		
	}

/*	// 鑾峰彇渚ц竟鏍廸ragment
	public LeftMenuFragment getLeftMenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(FRAGMENT_LEFT_MENU);

		return fragment;
	}*/

	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm
				.findFragmentByTag(FRAGMENT_CONTENT);

		return fragment;
	}

}
