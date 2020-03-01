package com.news163.Dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.news163.Bean.Article;

public class Datasql {
	Article act=null;
	List<Article> ls = new ArrayList(); 
	public void Decode(String json){
		JSONObject obj=JSONObject.parseObject(json);
//		 int Article_Id=obj.getInteger("id");
//		 String Article_Title=obj.getString("title");
//		 String Article_Source=obj.getString("source");
//		 String Right_Img=obj.getString("imgsrc");
//		 String Ptime=obj.getString("ptime");
//		 String Tag_Test=obj.getString("");
//		 String Reply_Count;
		
		//Object acv []=obj.getString("T1348647909107");
		List<Object> objList =JSON.parseArray(obj.getJSONArray("T1348647909107").toJSONString(),Object.class);
		//System.out.println(objList.size());
		//objList.size()+1;
		//System.out.println(objList.get(1));
		for(int i=0;i<objList.size();i++) {
			Object oop=objList.get(i);
			//System.out.println(oop.toString());
			JSONObject arrobj=JSONObject.parseObject(oop.toString());
			 String Cid=arrobj.getString("id");
			 String Article_Title=arrobj.getString("title");
			 String Article_Source=arrobj.getString("source");
			 
			 //获取多张图片
			 String Right_Img=arrobj.getString("imgsrc");
			 String imgnewextra =arrobj.getString("imgnewextra");
			 List<Object> kkk=new ArrayList<Object>();
			 kkk=JSON.parseArray(imgnewextra, Object.class);
			 //System.out.println(kkk);
			 if(kkk==null) {
//				
				// System.out.println("四三八");
				 //System.out.println(kkk);
			 }else if(kkk!=null){
				 System.out.println(kkk);
				 System.out.println("去你妈的");
				 for (int j = 0; j < kkk.size(); j++) {
						Object s= kkk.get(j);
						JSONObject xxxJsonObject=(JSONObject)s;
					//	JSONObject xxx=JSONObject.parseObject(s.toString());
						String ijString=xxxJsonObject.getString("imgsrc");
						Right_Img=Right_Img+","+ijString;
						System.out.println(Right_Img);
					}
			}
		
			 
			 String Ptime=arrobj.getString("ptime");
			 String Tag_Test=arrobj.getString("text");
			 String Reply_Count=arrobj.getString("replyCount");
			 act=new Article(Article_Title, Article_Source, Right_Img, Ptime, Tag_Test, Reply_Count,Cid);
			// System.out.println(act.toString());
			// System.out.println(act.getArticle_Title()+act.getPtime()+act.getArticle_Source());
			 ls.add(act);
			
		}

	}
	
	public void sqladd() {
		//System.out.println("hellow");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://106.13.122.252:3306/news?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		String sql="insert into articlelist(Article_title,Article_source,right_img,ptime,tag_Test,reply_Count,Cid)values(?,?,?,?,?,?,?)";
		for (int i = 0; i<ls.size(); i++) {
			
			 Article ac=ls.get(i);
			 try {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, ac.getArticle_Title());
				pstmt.setString(2, ac.getArticle_Source());
				pstmt.setString(3, ac.getRight_Img());
				pstmt.setString(4, ac.getPtime());
				pstmt.setString(5, ac.getTag_Test());
				pstmt.setString(6, ac.getReply_Count());
				pstmt.setString(7, ac.getCid());
				int x=pstmt.executeUpdate();
				if(x>0) {
					System.out.println("添加成功");
				}else {
					System.out.println("添加失败");
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		
		
		
		
	}
	public String getnews() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		List<Article> uo=new ArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://106.13.122.252:3306/news?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String sql="select * from articlelist order by Article_Id desc limit ?";
		JSONObject kpl=new JSONObject();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 6);
			result=pstmt.executeQuery();
			
			while(result.next()) {
				
				String title=result.getString("Article_title");
				String source=	result.getString("Article_source");
				String img=	result.getString("right_img");
				String ptime=	result.getString("ptime");
				String tag=result.getString("tag_Test");
				String reply=result.getString("reply_Count");
				String cid=result.getString("Cid");
				//System.out.println(title+source+img+ptime+tag+reply);
				Article okArticle=new Article(title, source, img, ptime, tag, reply, cid);
				uo.add(okArticle);
				//ojbk.put("Article", okArticle);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
				
		}
		kpl.put("object", uo);	
		//System.out.println(kpl.toString());
		return kpl.toString();
	
		
		
	}
	
}
