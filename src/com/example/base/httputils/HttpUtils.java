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

import android.content.Context;

public class HttpUtils {
private static  String resultStr=null; 
private static HttpClient httpClient;
/**
 * �ź��� post����
 * @param context
 * @param url
 * @param params
 * @return
 */
	public static String PostByHttpClient(final Context context, final String url,final Map<String, String> params) {
		System.out.println("HttpPost����URL��ӡ-->"+url);
		new Thread() {
			public void run() {
				try {
					if(!Utils.isNetWorkAvaiable(context)){
						resultStr=Constants.ERROR_NOAVAIABLE_NETWORK;
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
						}
					}
					hPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
							HTTP.UTF_8));
					try {
						HttpResponse httpResponse = httpClient.execute(hPost);
						if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
							//ȡ���ص��ַ���
							resultStr=EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);	
						}
						System.out.println("����������״̬���StatusCode-->"+httpResponse.getStatusLine().getStatusCode());
					} catch (ClientProtocolException e) {
						e.printStackTrace();
						resultStr="����:"+e.getMessage();
					} catch (IOException e) {
						e.printStackTrace();
						resultStr="����:"+e.getMessage();
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					resultStr="����:"+e.getMessage();
				}finally{
					if(httpClient!=null){
						httpClient.getConnectionManager().shutdown();
					}
					
				}
            System.out.println("���������ؽ��StrResult"+resultStr);
			};

		}.start();
     //return ConverErrorMessageShow(resultStr);
		return url;

	}
	/**
	 * �ź���  Get����
	 * @param context
	 * @param url
	 * @param fields
	 * @return
	 */
	public static String GetByHttpClient(final Context context,final String url,final Map<String, String>fields){
       new Thread(){
			public void run() {
				try {
					if(!Utils.isNetWorkAvaiable(context)){
						resultStr=Constants.ERROR_NOAVAIABLE_NETWORK;
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
					    System.out.println("���������ؽ��StrResult"+resultStr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(httpClient!=null){
						httpClient.getConnectionManager().shutdown();
					}
				}
				
			};
		}.start();
	
		return resultStr;
		
	}
	/**
	 * �˷�����ͣʹ��
	 * @param msg
	 * @return
	 */
	public static String ConverErrorMessageShow(String msg){
		if(msg.contains("refused")||msg.contains("time out")){
			return Constants.ERROR_NETWORK_TIMEOUT;
		}
		return msg;
		
	}
}
