package com.example.base.utils;

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

import com.example.exception.ConnectException;
import com.example.frag.R;

import android.content.Context;
import android.util.Log;

public class HttpUtils {
private static  String resultStr=null; 
private static HttpClient httpClient;
private static HttpResponse httpResponse;
private static AppCustomDialog dialog;
/**
 * �ź��� post����
 * @param context
 * @param url
 * @param params
 * @return
 */
public static String PostByHttpClient( Context context,  String url, Map<String, String> params) {
		try {
			Utils.isNetworkAvailable(context); 
			HttpParams httpParameters = new BasicHttpParams();
			
		    HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
		    HttpConnectionParams.setSoTimeout(httpParameters, 5000);  
			httpClient = new DefaultHttpClient(httpParameters);
			HttpPost hPost = new HttpPost(url);
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			if (null != params) {
				
				for (Map.Entry<String, String> entry : params.entrySet()) {
						nameValuePair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
						//URLƴ�� ͨ����ӡ�жϴ����Ƿ���ȷ
				url = url + entry.getKey() + "="+ new String(entry.getValue().toString().getBytes(), "iso-8859-1") + "&";
				}
			
		      System.out.println("URLƴ��----->"+url.substring(0,url.length()-1));
			}
		
			hPost.setEntity(new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8));
			
			try {
				 httpResponse = httpClient.execute(hPost);
				if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
					//ȡ���ص��ַ���
					resultStr=EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);	
				}
				//ע���ӡҪ��try��д ��һ����� ���������������ʱʱcode=null  ���д�������nullָ��
				System.out.println("StateCode:"+httpResponse.getStatusLine().getStatusCode()+":���ؽ��StrResult"+resultStr);

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
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			resultStr="����:"+e1.getMessage();
		}finally{
			if(httpClient!=null){
				httpClient.getConnectionManager().shutdown();
			}
			
		}
  		return resultStr;

	}
	/**
	 * �ź���  Get����
	 * @param context
	 * @param url
	 * @param fields
	 * @return
	 */
	public static String GetByHttpClient(final Context context,final String url,final Map<String, String>fields){
		
		try {
			Utils.isNetworkAvailable(context);		
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
			    System.out.println("StateCode:"+httpResponse.getStatusLine().getStatusCode()+":���ؽ��StrResult"+resultStr);

		} catch (Exception e) {
			e.printStackTrace();
			resultStr="����:"+e.getMessage();
		}finally{
			if(httpClient!=null){
				httpClient.getConnectionManager().shutdown();
			}
		}
       return resultStr;
		
	}

}
