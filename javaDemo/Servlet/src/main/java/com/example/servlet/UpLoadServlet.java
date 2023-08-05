package com.example.servlet;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/upload")
@MultipartConfig  // 如果是文件上传，一定要加这个注解
public class UpLoadServlet extends HttpServlet {

//    for web
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        设定编码
//        req.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset=utf-8");
//
//        Part part = req.getPart("myFile");
//        String submittedFileName = part.getSubmittedFileName();
//        String path = req.getSession().getServletContext().getRealPath("/") + submittedFileName;   // 这里上传到了运行的地方，而不是代码库的地方
//        System.out.println(path);
//        part.write(path);
//    }

//  for android
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设定编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");

        ServletInputStream inputStream = req.getInputStream();

//        String path = req.getSession().getServletContext().getRealPath("/") + "1.txt";
        String path = req.getSession().getServletContext().getRealPath("/") + "1.jpg";
        System.out.println(path);
        OutputStream outputStream = new FileOutputStream(path);

        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
    }
}
