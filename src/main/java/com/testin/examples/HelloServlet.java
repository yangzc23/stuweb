package com.testin.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testin.utils.DBUtil;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter pw = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("text/html;charset=utf-8");
		StringBuilder str = new StringBuilder();
		str.append("<table width='70%' border='1'><tr><th>学号</th><th>姓名</th><th>性别</th><th>生日</th><th>操作</th></tr>");
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM STUDENT");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4));
				str.append("<tr><td>"+rs.getInt(1)+"</td>"+
				"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+"</td>"+
				"<td>"+rs.getDate(4)+"</td>"+
				"<td><a href='detail?sid="+rs.getInt(1)+"'>详情</a>&nbsp;"+
				"<a href='edit?sid="+rs.getInt(1)+"'>编辑</a>&nbsp;"+ 
				"<a href='delete?sid="+rs.getInt(1)+"' onclick=\"return confirm('确定要删除该学生么?')\">删除</a>"+ 
				"</td></tr>");
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

		pw.println("<h1>欢迎使用学生管理系统</h1>"+str+"</table>");

	}
}
