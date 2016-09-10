package com.example.activity;

import org.apache.http.client.utils.URIUtils;

import com.example.base.utils.Utils;
import com.example.frag.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeListViewActiviity extends BaseActivity {
	String url;
	WebView webV;


	@Override
	public void initView() {
	
		View view = LayoutInflater.from(this).inflate(R.layout.webview, null);
		webV = (WebView) view.findViewById(R.id.webView1);
		ViewIsNetWorkState(view, 1);// Ìí¼Ówebview
		
	
		url=(String) getIntent().getExtras().get("url");
	
		
	}

	@Override
	public void initData() {
		WebSettings wSet = webV.getSettings();
		wSet.setJavaScriptEnabled(true);
		webV.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
					return true;
			//	return super.shouldOverrideUrlLoading(view, url);
			
			}
		});
		// Utils.ToastShort(getApplication(), url);
		webV.loadUrl(url);
		Utils.ToastShort(getApplicationContext(), url);
	}

}
