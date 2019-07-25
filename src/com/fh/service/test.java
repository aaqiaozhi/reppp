package com.fh.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class test {

	
	
	public static String test(String path){
		String result = "";
		
				 
		 try {
		     URL url = new URL(path);
		     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		     //get请求
		     conn.setRequestMethod("GET");
		     conn.setRequestProperty("accept", "*/*");
		     conn.setRequestProperty("connection", "Keep-Alive");
		     conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 
		     conn.setUseCaches(false);
		     conn.connect();
		     InputStream is = conn.getInputStream();
		     BufferedReader br = new BufferedReader(new InputStreamReader(is));
		     String str = "";
		     while ((str = br.readLine()) != null) {
		    	 result += str;
		     }
		     System.out.println("哈哈哈"+result);
		     is.close();
		     conn.disconnect();
		  } catch (Exception e) {
				e.printStackTrace();
			}

		return result;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 11; i++) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("keywords", "武林1号");
			jsonObject.put("city", "无锡市");
			jsonObject.put("types", "120000");
			JSONObject json=new JSONObject();
			json.put("token", null);
			json.put("data", jsonObject);
			String url="http://drp.kukahome.com/api/map/place?data="+json.toJSONString();
			test(url);
			
		}
		
				
	}
}
