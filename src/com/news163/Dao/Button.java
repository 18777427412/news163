package com.news163.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Button {

	public String hj() {
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
		String sql="select * from btn";
		String bnn=null;
		List<Object> dList=new ArrayList();
		try {
			pstmt=con.prepareStatement(sql);
			result=pstmt.executeQuery();
			while(result.next()) {
				bnn=result.getString("button");
				dList.add(bnn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("status", 1);
		json.put("xxxx", dList);
		return json.toString();
	
		
	}


}
