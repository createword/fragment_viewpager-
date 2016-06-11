package com.example.base.utils;

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
 * �̴߳���Http����
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
		// bInterface.dataSubmit();//�첽����UI����
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

		ConverErrorMessageShow(res);// �������������صĽ��

		if (dialog.isShowing()) {
			dialog.dismiss();
		}

	}

	/**
	 * zhm ��ʾ������Ϣ
	 * 
	 * @param msg
	 * @return
	 */
	public void ConverErrorMessageShow(String res) {
		JSONObject result = null;
		if(res!=null){//res������������ͻ��˷����κν������Ϊnull
			if (res.contains("timed out")) { // ������Ip����ȷʱ�ᳬʱ ���������ַ�Ƿ���ȷ
				Utils.ToastShort(context, Constants.ERROR_NETWORK_TIMEOUT);

			} else if (res.contains("refused")) {
				Utils.ToastShort(context, Constants.ERROR_NETWORKEXCEPTION);

			} else if (res.contains("NetWorkConnectException")) {
				Utils.ToastShort(context, Constants.ERROR_NOAVAIABLE_NETWORK);

			} else {
				try {
					result = new JSONObject(res);
					System.out.println("json��������-->" + result);
					bInterface.darSuccess(result);

				} catch (JSONException e) {
					e.printStackTrace();
					if (result == null) {
						bInterface.dataError("JsonResultΪ�ս���ʧ��");
					}

				}
			}
		}else{
			return ;
		}
		
	}
}
