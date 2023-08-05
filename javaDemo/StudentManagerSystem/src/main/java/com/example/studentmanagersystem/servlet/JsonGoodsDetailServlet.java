package com.example.studentmanagersystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "JsonGoodsDetailServlet", value = "/goods_detail")
public class JsonGoodsDetailServlet extends HttpServlet {  // 这里没有实现该方法，直接用，使用默认实现

    //    init方法是在Servlet实例化之后执行的，并且只执行一次
    public void init() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设定编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/javascript;charset=utf-8");

        String path = req.getSession().getServletContext().getRealPath("/goods_detail.json");
        getServletContext().log(path);
        File file = new File(path);
        if (file.exists() && file.isFile()) {
//            resp.setContentType("application/x-msdownload");  // 这句指明浏览器进行下载，而不是直接显示
//            resp.setHeader("Content-Disposition", "attachment; filename=\"" + musicName + "\"");  // 这句指明下载的文件名
            resp.setContentLengthLong(file.length());  // 附带发送文件长度
            OutputStream outputStream = resp.getOutputStream();
            InputStream inputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
        }
    }

    public void destroy() {
    }
}