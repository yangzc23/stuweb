package com.testin.examples;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter pw = null;
		//解决响应正文内容包含中文会出现乱码的问题
		resp.setContentType("application/json;charset=utf-8");
        try{
    		pw = resp.getWriter();
            //1.得到解析器工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            
            //2.得到解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            //3.判断上传表单的类型
            if(!ServletFileUpload.isMultipartContent(req)){
                //上传表单为普通表单，则按照传统方式获取数据即可
                return;
            }
            
            //为上传表单，则调用解析器解析上传数据
            List<FileItem> list = upload.parseRequest(req);  //FileItem
            
            //遍历list，得到用于封装第一个上传输入项数据fileItem对象
            for(FileItem item : list){
                
                if(item.isFormField()){
                    //得到的是普通输入项
                    String name = item.getFieldName();  //得到输入项的名称
                    String value = item.getString();
                    System.out.println(name + "=" + value);
                }else{
                    //得到上传输入项
                    String filename = item.getName();  //得到上传文件名  C:\Documents and Settings\ThinkPad\桌面\1.txt
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    String extName = filename.substring(filename.lastIndexOf("."));//.jpg
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    //新名称
                    String newName = uuid+extName;//在这里用UUID来生成新的文件夹名字，这样就不会导致重名
                    InputStream in = item.getInputStream();   //得到上传数据
                    int len = 0;
                    byte buffer[]= new byte[1024];
                    String savepath = this.getServletContext().getRealPath("/upload");
                    FileOutputStream out = new FileOutputStream(savepath + "\\" + newName);  //向upload目录中写入文件
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                    
                    in.close();
                    out.close();
                    pw.println("{\"result\":\"success\",\"message\":\"上传成功！\",\"url\":\"upload/"+newName+"\"}");
                }
            }
        
        }catch (Exception e) {
            e.printStackTrace();
            pw.print("{\"result\":\"fail\",\"message\":\"上传失败！\",\"url\":\"\"}");
        }
		

	}
}
