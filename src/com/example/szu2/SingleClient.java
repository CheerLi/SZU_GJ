package com.example.szu2;

import org.apache.http.impl.client.DefaultHttpClient;

public class SingleClient {
	private static DefaultHttpClient hc;
	
	private SingleClient(){
	}
	
	public synchronized static DefaultHttpClient getClient(){
		if(hc == null)
			hc = new DefaultHttpClient();
		return hc;
	}
}
