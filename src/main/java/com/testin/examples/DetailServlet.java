package com.testin.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testin.utils.DBUtil;

public class DetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		Connection conn = null;
		PreparedStatement stmt = null;
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("text/html;charset=utf-8");
		//
		PrintWriter pw = resp.getWriter();
		//
		int sid = Integer.parseInt(req.getParameter("sid"));
		//
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM STUDENT WHERE SNO=?");
			stmt.setInt(1, sid);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				pw.println("<head><meta charset='utf-8' /></head><body><h1>学生详细信息</h1>"+
						"<form action='update' method='post'>"+
						"<table width='70%'>"+					
						"<tr><td>头像：</td><td><img src='"+rs.getString(5)+"' width='120' height='120' readonly /></td></tr>"+						
						"<tr><td>学号：</td><td><input type='text' name='sno' value='"+rs.getInt(1)+"' readonly /></td></tr>"+
						"<tr><td>姓名：</td><td><input type='text' name='sname' value='"+rs.getString(2)+"' readonly /></td></tr>"+
						"<tr><td>性别：</td><td><input type='radio' name='gender' value='1' "+(rs.getString(3).equals("男")?"checked":"")+" />男"+ 
						"<input type='radio' name='gender' value='0'"+(rs.getString(3).equals("女")?"checked":"")+"/>女</td></tr>"+
						"<tr><td>生日：</td><td><input type='text' name='birth' value='"+rs.getDate(4)+"' readonly /></td></tr>"+
						"<tr><td></td><td><input type='submit' value='保存' disabled /></td></tr>"+		
						"</table>"	
						+ "</form></body>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
