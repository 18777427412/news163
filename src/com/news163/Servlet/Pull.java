package com.news163.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.news163.Bean.Article;
import com.news163.Dao.Datasql;
import com.news163.Dao.Getnews;

/**
 * Servlet implementation class Pull
 */
public class Pull extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str=new Getnews().SendGet("http://c.m.163.com/recommend/getSubDocPic?tid=T1348647909107&from=toutiao&offset=0&size=10&fn=11&LastStdTime=0&spestr=shortnews&prog=Rpic2&passport=&devId=0LeGDH2c%2BTKPUTYk3ca4qA%3D%3D&lat=cA34ftOajTcf8oiXnCnYSA%3D%3D&lon=hjgtNXtbGK4ek8fU60DW%2BQ%3D%3D&version=29.1&net=wifi&ts=1582469587&sign=cJrfEZPa0DXjxPQBttCEk3xYlLH3v1%2Fi2dFlEJ4NDbF48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=360_news&mac=YfGRPrrcososR3ciyMp0VgvVdjgSXYDtwEDZ03eH1l8%3D&open=&openpath=");
		//System.out.println(str);
		Datasql up=new Datasql();
		up.Decode(str);
		up.sqladd();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String st=up.getnews();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json charset=UTF-8");
		response.getWriter().write(st);
		//System.out.println(st);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
