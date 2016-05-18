package com.example.base.httputils;

import com.example.exception.ConnectException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utils {

	  /**
	   * zhanghengming
     * ��鵱ǰ�����Ƿ����
     * ע�� �����false ���쳣������  Ҳ��������ʱtrue û����ʱ���쳣
     * @param context
     * @return
     */
    
    public static boolean isNetworkAvailable(Context context)throws ConnectException
    {
        // ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (connectivityManager == null)
        {
        	throw new ConnectException("connectivityManager");
        
        }
        else
        {
            // ��ȡNetworkInfo����
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                   // System.out.println(i + "===״̬===" + networkInfo[i].getState());
                   // System.out.println(i + "===����===" + networkInfo[i].getTypeName());
                    // �жϵ�ǰ����״̬�Ƿ�Ϊ����״̬
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
	 * ��Tosat
	 * @param context
	 * @param msg
	 */
	public static void  ToastShort(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
}
