package com.example.szu2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.szu2.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	Date StartTime = new Date();
	RefreshableView refreshableView;
	ListView listView;
	ArrayList <ArrayList<String> > Content;
	public ArrayAdapter<String> adapter;
	final DefaultHttpClient hc = SingleClient.getClient();
	
	private long mExitTime = 0;
	@Override 
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		refreshableView = (RefreshableView)findViewById(R.id.refreshable_view);
		listView = (ListView)findViewById(R.id.list_view);
		try {
			Content = new GetTittles().execute("http://www.szu.edu.cn/board/").get();
			
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		} catch (ExecutionException e1) {

			e1.printStackTrace();
		}
		
		final List <String > Message = Content.get(0);
		final List <String > SubUrl = Content.get(1);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Message);
		/*开始：设置代理*/
/*		String proxyHost = "proxy.szu.edu.cn";
		int proxyPort = 8080;
		String userName = "114666";
		String password = "040639";
		hc.getCredentialsProvider().setCredentials(
		new AuthScope(proxyHost, proxyPort),
		new UsernamePasswordCredentials(userName, password));
		HttpHost proxy = new HttpHost(proxyHost,proxyPort);
		hc.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
*/		/*结束：设置代理*/
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				
				String ClickUrl = SubUrl.get(position);
			/*	HttpPost httpPost = new HttpPost(ClickUrl);
				HttpGetThread thread1 = new HttpGetThread(hc,httpPost,"GBK");
				thread1.start();
				while(thread1.isAlive()){
					//	Log.v("mylog", "获取数据");
				}
				Document doc = thread1.getDocument();
				
				Element e = doc.getElementsByAttributeValue("style", "border-collapse: collapse").first();
			*/	Intent MainToMessage =  new Intent(MainActivity.this,MessageActivity.class);
				//MainToMessage.setClass(MainActivity.this,MessageActivity.class);
				Bundle MessageBundle = new Bundle();
			//	MessageBundle.putString("message",e.toString());
				MessageBundle.putString("url",ClickUrl);
				MainToMessage.putExtras(MessageBundle);
				startActivity(MainToMessage);
			//	Log.v("message", e.toString());
			
			}
			
		});
		refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener(){
			@Override
			public void onRefresh(){
				try{
					Thread.sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		},0);
		Date EndTime = new Date();
		Log.v("time", (EndTime.getTime()-StartTime.getTime())+"");
	}
	@Override
	public boolean onKeyDown(int keyCode , KeyEvent event){
		//Toast.makeText(MainActivity.this, "" + mExitTime , Toast.LENGTH_SHORT).show();
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if((System.currentTimeMillis() - mExitTime) > 2000){
				Toast.makeText(MainActivity.this, "再按一次推出程序",Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			}else {
				finish();
			}
			
			return true;
		}
		return super.onKeyDown(keyCode, event);
		
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
	}
	class WriteToSD extends  AsyncTask < String ,Void ,Boolean >{

		Context context;
		WriteToSD(Context context){
			this.context = context;
		}
		@Override
		protected Boolean doInBackground(String... params) {
			
			String fileName = params[0];
			String content = params[1];
			SDCache sd = new SDCache(context);
			String sdPath = sd.getSDPATH();
			try {
				sd.createSDFile(fileName);
				sd.writeToSD(fileName, content);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		
	} 
	class GetTittles extends AsyncTask<String,Integer,ArrayList<ArrayList<String>>>{

		ArrayList<ArrayList<String>> Content ;
		ArrayList <String >  TempMessage ;
		ArrayList <String >  SubUrl ;

		 protected void onPreExecute(){	//在doInBackground(params...)之前被调用，在ui线程执行

				Content = new ArrayList<ArrayList<String>>();
				TempMessage = new ArrayList<String >();
				SubUrl = new ArrayList<String>();
		 }
		 
		@Override
		protected ArrayList<ArrayList<String>> doInBackground(String... params) {
			String url = params[0];
			HttpGet httpGet = new HttpGet(url);
			DefaultHttpClient httpClient = SingleClient.getClient();
			try {
				HttpResponse httpResponse= httpClient.execute(httpGet);
				HttpEntity entity = httpResponse.getEntity();
				String result = EntityUtils.toString(entity, "GBK");
				Document doc = Jsoup.parse(result);//消耗较多时间
				
				Elements table = doc.getElementsByAttributeValue("style", "border-collapse: collapse");
				Log.v("table", table.text().toString());
				Elements es = table.get(0).getElementsByTag("tr");	//出现一个bug，可能原校园网页被更改
				boolean bj = false;
				
				for(Element e: es){

					String text = e.text();
					if(!bj&&text.equals("序号 类别 发文单位 标题 附件 日期")){
						bj = true;
					}else if(bj){
						if(e.text().equals("")) continue;
						Elements es_td = e.getElementsByTag("td");
						java.util.Iterator<Element> it_td = es_td.iterator();
						String number ="";
						String type="";
						String department="";
						String title="";
						String urls_title="";
						String file="";
						String date="";
						while(it_td.hasNext()){
				
							number = it_td.next().text();
							
							if(it_td.hasNext())
								type =  it_td.next().text();
							if(it_td.hasNext())
								department =it_td.next().text();
							Element e_title = null;
							if(it_td.hasNext()){
								e_title = it_td.next();
								urls_title = "http://www.szu.edu.cn/board/"+e_title.select("a[href]").attr("href");
								title = e_title.text();
							}
							if(it_td.hasNext()){
								Element e_file = it_td.next();
							if(!e_file.select("img").attr("src").equals(""))
								file = "有附件" ;
							else 
								file = "无附件" ;
							}
							if(it_td.hasNext())
								date = it_td.next().text();
							TempMessage.add(date+" "+department+"\r\n"+title);
							SubUrl.add(urls_title);
						}
						
					}
				}
				Content.add(TempMessage);
				Content.add(SubUrl);
				return Content;
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			return null;
		}
		

		 protected void onPostExecute(ArrayList<ArrayList<String>> result){//后台任务执行完之后被调用，在ui线程执行
			
		 }
		 
	}
}
