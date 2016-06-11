package com.example.base.tab.pager;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.analysis.BaseAsyTaskInterface;
import com.example.base.utils.DoHttpAsyn;
import com.example.base.utils.IpUtils;
import com.example.base.utils.Utils;
import com.example.frag.R;
import com.example.viewpage.BasePager;

public class LoginPager extends BasePager implements OnClickListener {
	private EditText userEdit;
	private EditText passEdit;
	private Button btOk;
	private String url = IpUtils.MainIpServer + "/LoginUser/VerifyCode?";
	private Map<String, String> params;

	public LoginPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initView() {
		ViewIsNetWorkState();
		base_left.setVisibility(View.GONE);
		tvTitle.setText("µÇÂ½");
		flContent.addView(LayoutInflater.from(mActivity).inflate(
				R.layout.login_activity, null));
		userEdit = (EditText) mActivity.findViewById(R.id.userEdit_id);
		passEdit = (EditText) mActivity.findViewById(R.id.passEdit_id);
		btOk = (Button) mActivity.findViewById(R.id.OkButton_id);

	}

	@Override
	public void initData() {
		btOk.setOnClickListener(this);

	}

	public void onClick(View v) {
		String username = userEdit.getText().toString();
		String password = passEdit.getText().toString();
		params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		switch (v.getId()) {
		case R.id.OkButton_id:
			new DoHttpAsyn(mActivity, new BaseAsyTaskInterface() {

				public void dataError(String msg) {
					// TODO Auto-generated method stub

				}

				public void darSuccess(JSONObject result) {
					// TODO Auto-generated method stub

				}
			}).execute(url, params);
			break;

		default:
			break;
		}
	}
}
