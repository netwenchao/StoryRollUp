package com.luckywc.core.Net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpDownloader {
	
	/*
	 * Download Text file or webpage code from url
	 * */
	public static String DownLoadString(String strUrl){
		StringBuffer buffer=new StringBuffer();
		String line=null;
		BufferedReader bufferReader=null;
		try {
			URL url=new URL(strUrl);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			bufferReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line=bufferReader.readLine())!=null){
				buffer.append(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				bufferReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	} 

	public static void DownLoadFile(String strUrl){
		URL url;
		try {
			url = new URL(strUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//conn.getInputStream()
	}
	
	public enum FileDownLoadState{
		
	}
}
