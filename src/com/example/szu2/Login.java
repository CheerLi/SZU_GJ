package com.example.szu2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login extends Activity {
	private ImageView mImageView;
	private Button mButton;
	private ProgressBar mProgressBar;
	private ActionBar mActionBar;
	@Override 
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	//	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE );
		setContentView(R.layout.login_board);
	//	mActionBar .setDisplayShowHomeEnabled(false);
	//	mActionBar.setDisplayUseLogoEnabled(false);
		mImageView = (ImageView)findViewById(R.id.verifyImage);
		mButton = (Button)findViewById(R.id.button);
		mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
		/*
		 * 锟接伙拷锟斤拷锟叫讹拷取锟剿猴拷锟斤拷锟斤拷,锟斤拷锟斤拷校园锟斤拷证锟斤拷bug锟斤拷只要锟斤拷锟斤拷锟斤拷锟斤拷锟侥革拷锟街凤拷锟斤拷通锟斤拷锟斤拷证锟斤拷实锟斤拷锟皆讹拷锟斤拷录
		 */
	/*	SharedPreferences preferences = getSharedPreferences("user",Context.MODE_PRIVATE);
		String username = preferences.getString("username", "");
		String password = preferences.getString("password", "");
		if(!username .equals("") && !password .equals("")){
			((EditText)findViewById(R.id.username)).setText(username);
			((EditText)findViewById(R.id.password)).setText(password);;
			((EditText)findViewById(R.id.verifyInput)).setText("aaaa");;
			new SZUVerify().execute("https://auth.szu.edu.cn/cas.aspx/login?service=http://www.szu.edu.cn/manage/caslogin.asp?rurl=http://www.szu.edu.cn/board/",username,password);
	*/	/*
		 * 锟斤拷取锟斤拷锟斤拷
		 */
			
//		}else {
			/*
			 * 锟矫伙拷锟斤拷一锟轿碉拷录锟斤拷锟饺伙拷取锟斤拷证锟诫，锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟剿猴拷锟斤拷锟斤拷锟铰�
			 */
			new GetSZUVerifyImage().execute("https://auth.szu.edu.cn/gencheckcode.aspx");
//		}
		mButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				/*		
	        	paras.add(new BasicNameValuePair("username","liqihang"));
	        	paras.add(new BasicNameValuePair("password","liqihang"));
	        	paras.add(new BasicNameValuePair("quickforward","yes"));
	        	paras.add(new BasicNameValuePair("handlekey","ls"));
	      		HttpPost httpPost = new HttpPost("http://nbbs.szu.edu.cn/d/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes");  
				 */ 
				new SZUVerify().execute("https://auth.szu.edu.cn/cas.aspx/login?service=http://www.szu.edu.cn/manage/caslogin.asp?rurl=http://www.szu.edu.cn/board/");	
			}});
	}
	
	
	class SZUVerify extends AsyncTask<String ,Integer,Void>{

		@Override
		protected Void doInBackground(String... params) {
			String username;
			String password;
			String verify;
			if(1 == params.length ){
				username =( (EditText)findViewById(R.id.username) ).getText().toString();
				password =( (EditText)findViewById(R.id.password) ).getText().toString();
				verify = ( (EditText)findViewById(R.id.verifyInput) ).getText().toString();
			}else {
				username = params[1];
				password = params[2];
				/*
				 * 学校锟斤拷证漏锟斤拷锟斤拷只要锟斤拷锟斤拷锟侥革拷锟街凤拷锟斤拷通锟斤拷锟教拷锟街わ拷锟斤拷锟斤拷证
				 */
				verify = "ddgd";
			}
			
			final DefaultHttpClient hc = SingleClient.getClient();
			List<NameValuePair> paras = new ArrayList<NameValuePair>();
			
			paras.add(new BasicNameValuePair("__VIEWSTATE","/wEPDwUJOTA1MDcxMTYxD2QWAmYPZBYCZg8WAh4HVmlzaWJsZWhkZPTiJ1ieJIOMLcDGTnIyNRCVvBXC"));//%2FwEPDwUJOTA1MDcxMTYxD2QWAmYPZBYCZg8WAh4HVmlzaWJsZWhkZPTiJ1ieJIOMLcDGTnIyNRCVvBXC
	        paras.add(new BasicNameValuePair("__EVENTVALIDATION","/wEWBQK6wNS7AQKarebFCwLKw6LdBQLChPzDDQLCi9reA++KYQyQx+uRBKa/mMfCawKROKzL"));//%2FwEWBQK6wNS7AQKarebFCwLKw6LdBQLChPzDDQLCi9reA%2B%2BKYQyQx%2BuRBKa%2FmMfCawKROKzL
	        paras.add(new BasicNameValuePair("txtcardno",username));
	        paras.add(new BasicNameValuePair("txtPass",password));
	        paras.add(new BasicNameValuePair("txtCode",verify));
	        paras.add(new BasicNameValuePair("btnSubmit","锟斤拷录"));//%E7%99%BB%E5%BD%95
			final HttpPost httpPost = new HttpPost(params[0]);
			
			int status = 0;
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(paras,"UTF-8"));
				HttpResponse response = hc.execute(httpPost);
				status = response.getStatusLine().getStatusCode();
				if(200 == status || 301 == status ||302 == status ){		//ps:锟斤拷证锟斤拷锟斤拷锟揭诧拷芊锟斤拷剩锟�
				
					/*
					 * 锟斤拷锟斤拷锟剿猴拷锟斤拷锟诫到锟斤拷锟斤拷锟叫ｏ拷实锟斤拷锟皆讹拷锟斤拷录
					 */
					SharedPreferences preferences = getSharedPreferences("user",Context.MODE_PRIVATE);
					Editor editor = preferences.edit();
					editor.putString("username",username );
					editor.putString("password", password);
					editor.commit();
					/*
					 * 锟斤拷锟斤拷锟剿猴拷锟斤拷锟斤拷锟斤拷锟�
					 */
					//Log.v("status", ""+status);
					Intent intent = new Intent();
					Bundle bundle = new Bundle();			
					intent.putExtras(bundle);
					intent.setClass(Login.this,MainActivity.class);
					startActivity(intent);
				}else {
					Toast.makeText(Login.this, response.getEntity().getContent().toString(), Toast.LENGTH_LONG).show();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
			finish();
		}
		
	}
	
	
	
	class GetSZUVerifyImage extends AsyncTask<String,Integer,Bitmap>{

		

		 protected void onPreExecute(){	//锟斤拷doInBackground(params...)之前锟斤拷锟斤拷锟矫ｏ拷锟斤拷ui锟竭筹拷执锟斤拷
			 mImageView.setImageBitmap(null);
			 mProgressBar.setProgress(0);//锟斤拷锟斤拷锟斤拷锟轿�
		 }
		 
		 
		@Override
		protected Bitmap doInBackground(String... params) {	//锟斤拷锟斤拷锟教ㄖ达拷械锟斤拷锟斤拷锟斤拷诤锟教拷叱锟斤拷锟�
			publishProgress(0);//锟斤拷锟斤拷锟斤拷锟給nProgressUpdate(Integer... progress)锟斤拷锟斤拷
			DefaultHttpClient hc = SingleClient.getClient();
			
			publishProgress(30);
			HttpGet hg = new HttpGet(params[0]);
			Bitmap bm = null;
			
				HttpResponse hr = null;
				try {
					hr = hc.execute(hg);
					bm = BitmapFactory.decodeStream(hr.getEntity().getContent());
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			publishProgress(100);
			return bm;
		}
		protected void onProgressUpdate(Integer... progress){//锟节碉拷锟斤拷publishProgress之锟襟被碉拷锟斤拷执锟斤拷
			mProgressBar.setProgress(progress[0]);//锟斤拷锟铰斤拷锟斤拷锟斤拷慕锟斤拷
		}
		protected void onPostExecute(Bitmap result){//锟斤拷台锟斤拷锟斤拷执锟斤拷锟斤拷之锟襟被碉拷锟矫ｏ拷锟斤拷ui锟竭筹拷执锟斤拷
			 if(result != null){
				 Toast.makeText(Login.this, "请登录",Toast.LENGTH_LONG).show();
				 mImageView.setImageBitmap(result);
			 }else {
				 Toast.makeText(Login.this, "获取验证码失败，请重新获取", Toast.LENGTH_LONG).show();
				// new GetSZUVerifyImage().execute("https://auth.szu.edu.cn/gencheckcode.aspx");
			 }
		 }
		 
		 protected void onCancelled(){
			 //锟斤拷ui锟竭筹拷执锟斤拷
			 mProgressBar.setProgress(0);//锟斤拷锟斤拷锟斤拷锟轿�
		 }
				
	}
	
	
	/*
	 * MainActivity.onDestroy()
	 */
	@Override
    protected void onDestroy(){
        super.onDestroy();

    }
}
