package com.example.exception;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * ������������Ƿ��쳣
 * @author WINTER
 *
 */
public class ConnectException extends Exception {
	public ConnectException(String msg) {
		super(msg);
	}
}
