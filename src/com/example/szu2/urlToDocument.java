package com.example.szu2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.Bundle;
import android.util.Log;


public class urlToDocument {
	public static Document getDocument(DefaultHttpClient hc,HttpPost httpPost,String charSet){
		HttpResponse response;
		try {
			response = hc.execute(httpPost);
			HttpEntity he=response.getEntity();
			String result = EntityUtils.toString(he, charSet);
			Document doc = Jsoup.parse(result);
			return doc;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	/*	Document doc=null;
		while(doc==null){
			InputStream is = null;
			try {
				HttpResponse response=hc.execute(httpPost);
				HttpEntity he=response.getEntity();
				Log.v("status", ""+response.getStatusLine().getStatusCode());
		//		String result = EntityUtils.toString(he, charSet);
		//		doc = Jsoup.parse(result);
				StringBuffer result = new StringBuffer();
				is = he.getContent();
				
				int COUNT = 4096;
				byte []temp = new byte[COUNT];	//如果开的空间太小，会读取重复内容,或读取不够数据，原因？
				int len = -1;
				while((len = is.read(temp))!=-1){
					result.append(new String(temp,charSet));
					temp = new byte[COUNT];
	//				Log.v("doc", new String(temp,"GBK"));
				}
		
				doc=Jsoup.parse(result.toString());
							
			}catch(UnknownHostException e){
				e.printStackTrace();
			}catch(SocketTimeoutException e){
				//e.printStackTrace();
			}catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(is != null)
						is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return doc;
	*/	
	}
	
}
