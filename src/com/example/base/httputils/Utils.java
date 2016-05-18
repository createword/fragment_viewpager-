package com.example.base.httputils;

import com.example.exception.ConnectException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utils {

	  /**
	   * zhanghengming
     * 检查当前网络是否可用
     * 注意 这里的false 用异常代替了  也就是联网时true 没连网时抛异常
     * @param context
     * @return
     */
    
    public static boolean isNetworkAvailable(Context context)throws ConnectException
    {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (connectivityManager == null)
        {
        	throw new ConnectException("connectivityManager");
        
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                   // System.out.println(i + "===状态===" + networkInfo[i].getState());
                   // System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        throw new ConnectException("NetWorkConnectException");
    
    }
	/**
	 * 短Tosat
	 * @param context
	 * @param msg
	 */
	public static void  ToastShort(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
