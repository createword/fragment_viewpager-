package com.example.base.httputils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

	/*
	 * ≈–∂œÕ¯¬Á «∑Òø…”√
	 */
	public static boolean isNetWorkAvaiable(Context context){
		if(context==null)return false;
		ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(manager==null){return false;}
		NetworkInfo info=manager.getActiveNetworkInfo();
		if ((info != null) && (info.isConnected()) && (info.getState() == NetworkInfo.State.CONNECTED)) {
			return true;
		} else {
			return false;
		}
		
	}
}
