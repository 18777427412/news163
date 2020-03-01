package com.news163.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Cqiehuan {
	public String qiehuan(){
		Connection con=null;
		ResultSet result=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://106.13.122.252:3306/news?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","root");
		}catch ( ClassNotFoundException e) {
			// TODO: handle exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="select * from lanmu";
		String lanmu=null;
		int id=0;
		List<Object> kl= new ArrayList();
		try {
			pstmt= con.prepareStatement(sql);
			result=pstmt.executeQuery();
			//id=result.getInt("id");
			
			while(result.next()) {
				lanmu=result.getString("lanmu");
				//System.out.println(lanmu);
				kl.add(lanmu);
				
			}
//			System.out.println(kl.size());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(con!=null)con.close();
				if(pstmt!=null)result.close();
				if(result!=null)result.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		JSONObject d=new JSONObject();
		Object statObject=1;
		d.put("status", statObject);
		d.put("lanmu", kl);
		
		//System.out.println(d);
		return d.toString();	
			
			
		
	}
}
