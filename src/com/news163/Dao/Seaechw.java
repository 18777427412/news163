package com.news163.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.news163.Bean.Article;

public class Seaechw {
	public String sea(String searchString) {
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
		String sql="select * from articlelist where Article_title like ?";
		String bnn=null;
		List<Object> dList=new ArrayList<Object>();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchString+"%");
			result=pstmt.executeQuery();
			System.out.println(result.getRow());
			while(result.next()) {
				//时间 标题 跟帖 来源
				String ptime=result.getString("ptime");
				String Article_title=result.getString("Article_title");
				String Article_source=result.getString("Article_source");
				String right_img=result.getString("right_img");
				String reply_Count=result.getString("reply_Count");
				Article dobject=new Article(Article_title, Article_source, right_img, ptime, "", reply_Count, "");
				dList.add(dobject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(dList);
		JSONObject json=new JSONObject();
		json.put("status", 1);
		json.put("搜到文章", dList);
		return json.toString();
	}
}
