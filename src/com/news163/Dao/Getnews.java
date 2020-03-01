package com.news163.Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class Getnews {
	public String SendGet(String url_get) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String json = null;
		HttpURLConnection con=null;
		 String wen=null;
		try {
			 URL url = new URL(url_get);
	            //通过远程url连接对象打开一个连接，强转成HTTPURLConnection类
	            con = (HttpURLConnection) url.openConnection();
	            con.setRequestMethod("GET");
	            //设置连接超时时间和读取超时时间
	            con.setConnectTimeout(15000);
	            con.setReadTimeout(60000);
	            con.setRequestProperty("Accept", "application/json");
	            //发送请求
	            con.connect();
		
			//System.out.println(con);
//			out=new OutputStreamWriter(con.getOutputStream());
//			out.write("");
//			out.flush();
//          out.close();
           
            if(200==con.getResponseCode()) {
            	//System.out.println("hellow");
            	in=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            	//System.out.println(in.readLine());
//            	while((wen=in.readLine())!=null) {
//            		json.append(json);s
//            	}
            	json=in.readLine();
            }else {
				System.out.println("code错误");
			}
			
			
		} catch (IOException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(in!=null)in.close();
				if(out!=null)out.close();
			
				
			} catch (IOException e2) {
				// TODO: handle exception
			}
		}
		//System.out.println(json);
		//System.out.println(wen);
		return json.toString();
		
	}
}
