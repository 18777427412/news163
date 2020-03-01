package com.news163.Bean;


import java.util.Date;

public class Article {
	private int Article_Id;
	private String Article_Title;
	private String Article_Source;
	private String Right_Img;
	private String Ptime;
	private String Tag_Test;
	private String Reply_Count;
	private String Cid;
	
	
	
	
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	public Article(String article_Title, String article_Source, String right_Img, String ptime,
			String tag_Test, String reply_Count,String cid) {
		Article_Title = article_Title;
		Article_Source = article_Source;
		Right_Img = right_Img;
		Ptime = ptime;
		Tag_Test = tag_Test;
		Reply_Count = reply_Count;
		Cid=cid;
	}
	public int getArticle_Id() {
		return Article_Id;
	}
	public void setArticle_Id(int article_Id) {
		Article_Id = article_Id;
	}
	public String getArticle_Title() {
		return Article_Title;
	}
	public void setArticle_Sitle(String article_Sitle) {
		Article_Title = article_Sitle;
	}
	public String getArticle_Source() {
		return Article_Source;
	}
	public void setArticle_Source(String article_Source) {
		Article_Source = article_Source;
	}
	public String getRight_Img() {
		return Right_Img;
	}
	public void setRight_Img(String right_Img) {
		Right_Img = right_Img;
	}
	public String getPtime() {
		return Ptime;
	}
	public void setPtime(String ptime) {
		Ptime = ptime;
	}
	public String getTag_Test() {
		return Tag_Test;
	}
	public void setTag_Test(String tag_Test) {
		Tag_Test = tag_Test;
	}
	public String getReply_Count() {
		return Reply_Count;
	}
	public void setReply_Count(String reply_Count) {
		Reply_Count = reply_Count;
	}
	@Override
	public String toString() {
		
		return Article_Title+Article_Source+"\t"+Right_Img+Ptime +"\t"+Tag_Test +"\t"+Reply_Count +"\t"+Cid;
	}
	
	
}
