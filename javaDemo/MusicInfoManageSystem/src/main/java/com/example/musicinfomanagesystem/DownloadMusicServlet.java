package com.example.musicinfomanagesystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "DownloadMusicServlet", value = "/download-servlet")
public class DownloadMusicServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设定编码
        req.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset=utf-8");

        String musicName = req.getParameter("musicName");
        String path = req.getSession().getServletContext().getRealPath("/") + musicName;
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
}
