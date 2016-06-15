package com.example.base.tab.pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.analysis.AUserLogin;
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
	private AUserLogin aUserLogin;
	private Map<String, Object> mapArray;
	private LinearLayout regist_;

	public LoginPager(Activity activity) {
		super(activity);

	}

	@Override
	public void initView() {
		// ViewIsNetWorkState();
		base_left.setVisibility(View.GONE);
		tvTitle.setText("登陆");
		flContent.addView(LayoutInflater.from(mActivity).inflate(
				R.layout.login_activity, null));
		userEdit = (EditText) mActivity.findViewById(R.id.userEdit_id);
		passEdit = (EditText) mActivity.findViewById(R.id.passEdit_id);
		btOk = (Button) mActivity.findViewById(R.id.OkButton_id);
		regist_ = (LinearLayout) mActivity.findViewById(R.id.regist_id);

	}

	@Override
	public void initData() {
		aUserLogin = new AUserLogin();
		regist_.setOnClickListener(this);
		btOk.setOnClickListener(this);

	}

	public void onClick(View v) {
		String username = userEdit.getText().toString().trim();
		String password = passEdit.getText().toString().trim();
		params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		switch (v.getId()) {
		case R.id.OkButton_id:
			if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
				Utils.ToastShort(mActivity, "用户名和密码不能为空");
			} else {
				new DoHttpAsyn(mActivity, new BaseAsyTaskInterface() {

					public void dataError(String msg) {
						// TODO Auto-generated method stub
						Utils.ToastShort(mActivity, msg);
					}

					public void darSuccess(JSONObject result) {
						mapArray = aUserLogin.setUserPara(result);
						if ((Boolean) mapArray.get("isflag")) {
							Utils.ToastShort(mActivity, "验证成功");
						} else {
							Utils.ToastShort(mActivity,
									(String) mapArray.get("msg"));
						}

					}
				}).execute(url, params);
			}

			break;
		case R.id.regist_id:
			Utils.ToastShort(mActivity, "此功能暂未开放...");
			break;
		default:
			break;
		}
	}
}
