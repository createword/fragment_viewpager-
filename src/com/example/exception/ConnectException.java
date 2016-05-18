package com.example.exception;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * 检测网络连接是否异常
 * @author WINTER
 *
 */
public class ConnectException extends Exception {
	public ConnectException(String msg) {
		super(msg);
	}
}
