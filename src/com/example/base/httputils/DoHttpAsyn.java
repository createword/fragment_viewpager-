package com.example.base.httputils;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.analysis.BaseAsyTaskInterface;
import com.example.frag.R;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * 线程处理Http请求
 * 
 * @author zhanghengming
 * 
 */
public class DoHttpAsyn extends AsyncTask<Object, Void, String> {
	private Context context;
	private BaseAsyTaskInterface bInterface;
	private Handler myHandler;
	private AppCustomDialog dialog;

	public DoHttpAsyn(Context context, BaseAsyTaskInterface bInterface) {
		this.context = context;
		this.bInterface = bInterface;
		dialog = new AppCustomDialog(context, R.layout.progress_dialog,
				R.style.DialogTheme);
	}

	@Override
	protected void onPreExecute() {
		// bInterface.dataSubmit();//异步请求UI操作
		dialog.show();
	}

	@Override
	protected String doInBackground(Object... params) {
		String url = (String) params[0];
		Map<String, String> map = (Map<String, String>) params[1];
		return HttpUtils.PostByHttpClient(context, url, map);
	}

	@SuppressWarnings("unused")
	@Override
	protected void onPostExecute(String res) {
		JSONObject result = null;
		try {
			ConverErrorMessageShow(res);
			result = new JSONObject(res);
			if (result == null) {
				bInterface.dataError();
			} else {
				bInterface.darSuccess(result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

	}

	/**
	 * zhm 显示错误信息
	 * 
	 * @param msg
	 * @return
	 */
	public void ConverErrorMessageShow(String msg) {
		if (msg.contains("timed out")) { //当请求Ip不正确时会超时 请检查请求地址是否正确
			Utils.ToastShort(context, Constants.ERROR_NETWORK_TIMEOUT);
		} else if (msg.contains("refused")) {
			Utils.ToastShort(context, Constants.ERROR_NETWORKEXCEPTION);
		} else if (msg.contains("NetWorkConnectException")) {
			Utils.ToastShort(context, Constants.ERROR_NOAVAIABLE_NETWORK);
		}
	}
}
