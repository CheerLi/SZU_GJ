package com.example.szu2;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.nodes.Document;

import android.os.Bundle;
import android.util.Log;

public class HttpGetThread extends Thread{
	DefaultHttpClient hc ;
	HttpPost httpPost ;
	Document doc;
	boolean end ;
	String charSet ;
	HttpGetThread(DefaultHttpClient hc,HttpPost httpPost,String charSet){
		this.hc = hc;
		this.httpPost = httpPost;
		this.charSet = charSet;
		this.end = false;
	}
	public void run(){
		doc = urlToDocument.getDocument(hc,httpPost,charSet);
		end = true;
	}
	public Document getDocument(){
		return doc;
	}
}
