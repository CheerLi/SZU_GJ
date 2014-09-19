package com.example.szu2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

public class SDCache{
	private Context context;
	
	/*
	 * 	SD���Ƿ���� 
	 */
	private boolean hasSD = false;
	
	/*
	 * SD����·��
	 */
	private String SDPATH;
	
	/*
	 *��ǰ�������·��
	 */
	private String FILESPATH;
	
	
	public SDCache(Context context){
		this.context = context;
		
		hasSD = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		
		SDPATH = Environment.getRootDirectory().getPath();
		
		FILESPATH = this.context.getFilesDir().getPath();
	}
	
	/*
	 * ��SD���ϴ����ļ�
	 * 
	 * @throw IOException
	 */
	
	public File createSDFile(String fileName) throws IOException{
		
		File file = new File(SDPATH);
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		return file;
	}
	
	/*
	 * ɾ��SD���ϵ��ļ�
	 * 
	 * @param fileName
	 */
	
	public boolean deleteSDFile(String fileName){
		
		File file = new File(SDPATH + "//" + fileName);
		
		if(file == null || !file.exists() || file.isDirectory())
			return false ;
		
		return file.delete();
	}
	
	
	/*
	 * ��ȡSD�����ı��ļ�
	 * 
	 * @param fileName
	 * @return 
	 */
	
	public String readSDFile(String fileName) throws IOException{
		
		StringBuffer sb = new StringBuffer();
		
		File file = new File(SDPATH + "//" + fileName);
		
		FileInputStream fis = new FileInputStream(file);
		
		int c;
		
		while((c = fis.read()) != -1){
			sb.append((char) c);
		}
		
		fis.close();
	
		
		return sb.toString();
	}
	
	/*
	 * д���ļ���SD��
	 * 
	 * @fileName
	 * @content
	 */
	public boolean writeToSD(String fileName,String content) throws IOException{
		File file = new File(SDPATH + "//" + fileName);
		OutputStream out;
		out = new FileOutputStream(file);
		out.write(content.getBytes());
		out.close();
		return true;

	}
	public String getFILESPATH(){
		return FILESPATH;
	}
	
	public String getSDPATH(){
		return SDPATH;
	}
	
	public boolean hasSD(){
		return hasSD;
	}
}
