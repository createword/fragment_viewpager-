package com.example.base.httputils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.frag.R;

import android.content.Context;
import android.util.Log;

public class HttpUtils {
private static  String resultStr=null; 
private static HttpClient httpClient;
private static AppCustomDialog dialog;
/**
 * 张恒铭 post请求
 * @param context
 * @param url
 * @param params
 * @return
 */
public static String PostByHttpClient( Context context,  String url, Map<String, String> params) {
		try {
			if(!Utils.isNetworkAvailable(context)){
				resultStr="NetWorkConnectException";
			} 
			
			HttpParams httpParameters = new BasicHttpParams();
		    HttpConnectionParams.setConnectionTimeout(httpParameters, 2000);
		    HttpConnectionParams.setSoTimeout(httpParameters, 2000);  
			httpClient = new DefaultHttpClient(httpParameters);
			HttpPost hPost = new HttpPost(url);
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			if (null != params) {
				
				for (Map.Entry<String, String> entry : params.entrySet()) {
						nameValuePair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
						//URL拼串 通过打印判断传参是否正确
						url = url + entry.getKey() + "="+ new String(entry.getValue().toString().getBytes(), "iso-8859-1") + "&";
				}
				LogUtils.d("URL拼串----->"+url.substring(0,url.length()-1));
		      
			}
		
			hPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8));
			
			try {
				HttpResponse httpResponse = httpClient.execute(hPost);
				if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					//取返回的字符串
					resultStr=EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);	
				}
				System.out.println("服务器返回状态结果StatusCode-->"+httpResponse.getStatusLine().getStatusCode());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				resultStr="错误:"+e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				resultStr="错误:"+e.getMessage();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			resultStr="错误:"+e.getMessage();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			resultStr="错误:"+e1.getMessage();
		}finally{
			if(httpClient!=null){
				httpClient.getConnectionManager().shutdown();
			}
			
		}
    System.out.println("服务器返回结果StrResult"+resultStr);
	
     //return ConverErrorMessageShow(resultStr);
		return ConverErrorMessageShow(resultStr);

	}
	/**
	 * 张恒铭  Get请求
	 * @param context
	 * @param url
	 * @param fields
	 * @return
	 */
	public static String GetByHttpClient(final Context context,final String url,final Map<String, String>fields){

		try {
			if(!Utils.isNetworkAvailable(context)){
				resultStr="NetWorkConnectException";;
			}
		
			httpClient=new  DefaultHttpClient();
			List<BasicNameValuePair>ListParams=new ArrayList<BasicNameValuePair>();
				if (null != fields) {
					for (Map.Entry<String, String> entry : fields.entrySet()) {
						ListParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}		
			}
				String params =URLEncodedUtils.format(ListParams, HTTP.UTF_8);
			    HttpGet  hGet=new  HttpGet(url+"?"+params);
			    HttpResponse httpResponse = httpClient.execute(hGet);
			    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = httpResponse.getEntity();
                    resultStr = EntityUtils.toString(entity, "utf-8");  
                }
			    System.out.println("服务器返回结果StrResult"+resultStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultStr="错误:"+e.getMessage();
		}finally{
			if(httpClient!=null){
				httpClient.getConnectionManager().shutdown();
			}
		}
		
		
		return resultStr;
		
	}
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public static String ConverErrorMessageShow(String msg){
		if(msg.contains("time out")){
			return Constants.ERROR_NETWORK_TIMEOUT;
		}else if(msg.contains("refused")){
			return Constants.ERROR_NETWORKEXCEPTION;
		}else if(msg.contains("NetWorkConnectException")){
			return Constants.ERROR_NETWORKEXCEPTION;
		}
		
		return msg;
		
	}
}
