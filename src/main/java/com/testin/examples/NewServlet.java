package com.testin.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		//
		pw.println("<!DOCTYPE html><head><meta charset='utf-8' />"+ 
		"<script src='js/jquery-3.4.1.min.js'>"+ 
		"</script><script src='js/demo.js'></script>"+ 
		"</head><body><h1>注册学生信息</h1>"+
		"<form action='save' method='post'>"+
		"<table width='70%'>"+
		"<tr><td>姓名：</td><td><input type='text' name='sname'/></td></tr>"+
		"<tr><td>性别：</td><td><input type='radio' name='gender' value='1' checked />男<input type='radio' name='gender' value='0'/>女</td></tr>"+
		"<tr><td>生日：</td><td><input type='date' name='birth'/></td></tr>"+
		"<tr><td>上传头像：</td><td><input type='file' name='photo' id='photo' />&nbsp;<input type='button' value='上传' onclick='f();' /></td></tr>"+	
		"<tr hidden><td></td><td><input type='text' name='filePath' id='filePath' /></td></tr>"+
		"<tr><td></td><td><img width='120' height='120' src='images/default.png' id='photo2'/></td></tr>"+		
		"<tr><td></td><td><input type='submit' value='保存'/></td></tr>"+		
		"</table>"	
		+ "</form></body>");
	}
}
