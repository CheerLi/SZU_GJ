package com.example.szu2;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.szu2.R;
















import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageActivity extends Activity {
	private ImageView back ; 
	private ActionBar mActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		Bundle GetMessage = this.getIntent().getExtras();
		final String url = GetMessage.getString("url");
		String MESSAGE = null;
		try {
			MESSAGE = new GetMessage().execute(url).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		TextView tv = (TextView)findViewById(R.id.text);
	    tv.setText(Html.fromHtml(MESSAGE));
	//	mActionBar = getSupportActionBar();
	//	setActionBarLayout(R.layout.actionbar_message_layout);
	//	back = (ImageView)findViewById(R.id.back);
	/*	back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
	*/	
	}
	

	/**
	* 璁剧疆ActionBar鐨勫竷灞�
	* @param layoutId 甯冨眬Id
	* 
	* */
	public void setActionBarLayout( int layoutId ){
	    if( null != mActionBar ){
	    	mActionBar.setDisplayShowHomeEnabled( false );
	    	mActionBar.setDisplayShowCustomEnabled(true);
			mActionBar.setIcon(android.R.color.transparent);
	        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View v = inflator.inflate(layoutId, null);
	        ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	        mActionBar.setCustomView(v,layout);
	    }
	}
	class GetMessage extends  AsyncTask < String ,Void ,String >{

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			HttpPost httpPost = new HttpPost(url);
			DefaultHttpClient httpClient = SingleClient.getClient();
			
				HttpResponse httpResponse;
				try {
					httpResponse = httpClient.execute(httpPost);
					HttpEntity entity = httpResponse.getEntity();
					String message = EntityUtils.toString(entity, "GBK");
					Document doc = Jsoup.parse(message);
					Element e = doc.getElementsByAttributeValue("style", "border-collapse: collapse").first();
					return e.toString();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return null;
		}
		
	}
/*	@Override 
	protected void onStart(){
		super.onStart();
		Bundle GetMessage = this.getIntent().getExtras();
		final String MESSAGE = GetMessage.getString("message");
		Log.v("message", MESSAGE);
		TextView tv = (TextView)findViewById(R.id.text);
	    tv.setText(Html.fromHtml(MESSAGE));
*///	    String text = tv.getText().toString();
//	    Log.v("message", "dd"+text);
/*	    Button BtBack = (Button)findViewById(R.id.BtBack);
	    BtBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
	    	
	    });
*///	}
}
