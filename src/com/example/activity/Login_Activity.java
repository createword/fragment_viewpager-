package com.example.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

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

public class Login_Activity extends BaseActivity implements OnClickListener {
	private EditText userEdit;
	private EditText passEdit;
	private Button btOk;
	private String url = IpUtils.MainIpServer + "/LoginUser/VerifyCode?";
	private Map<String, String> params;
	private AUserLogin aUserLogin;
	private Map<String, Object> mapArray;
	private LinearLayout regist_;
	public static boolean isFlag = false;

	@Override
	public void initView() {
		ViewIsNetWorkState(LayoutInflater.from(this).inflate(R.layout.login_activity, null), 0);
		userEdit = (EditText) findViewById(R.id.userEdit_id);
		passEdit = (EditText) findViewById(R.id.passEdit_id);
		btOk = (Button) findViewById(R.id.OkButton_id);
		regist_ = (LinearLayout) findViewById(R.id.regist_id);
	}

	@Override
	public void initData() {
		act_base_title.setText("登陆");
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
				Utils.ToastShort(this, "用户名和密码不能为空");
			} else {
				new DoHttpAsyn(this, new BaseAsyTaskInterface() {

					public void dataError(String msg) {
						// TODO Auto-generated method stub
						Utils.ToastShort(Login_Activity.this, msg);
					}

					public void darSuccess(JSONObject result) {
						mapArray = aUserLogin.setUserPara(result);
						if ((Boolean) mapArray.get("isflag")) {
							Utils.ToastShort(Login_Activity.this, "验证成功");
					
							Login_Activity.isFlag = true;
						} else {
							Login_Activity.isFlag = false;
							Utils.ToastShort(Login_Activity.this,(String) mapArray.get("msg"));
						}

					}
				}).execute(url, params);
			}

			break;
		case R.id.regist_id:
			Utils.ToastShort(this, "此功能暂未开放...");
			break;
		default:
			break;
		}
	
		
	}

}
