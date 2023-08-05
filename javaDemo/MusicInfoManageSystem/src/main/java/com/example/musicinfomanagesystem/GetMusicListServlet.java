package com.example.musicinfomanagesystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "GetMusicListServlet", value = "/getMusicList-servlet")
public class GetMusicListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设定编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String path = req.getSession().getServletContext().getRealPath("");
        getServletContext().log(path);
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            String[] names = file.list();  // 获取文件夹下的文件
            String content;
            if (names != null) {
                int namesLen = filter(names);
                content = stringArrayToString(names, 0, namesLen);
            } else {
                content = "";
            }
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write(content.getBytes());
            outputStream.close();
        }
    }

    private int filter(String[] names) {  // 过滤出mp3文件
        int i = 0, j = 0;
        while (j < names.length) {
            if (names[j].endsWith(".mp3"))
                names[i++] = names[j];
            j ++;
        }
        return i;
    }

    private String stringArrayToString(String[] names, int offSet, int len) {
        StringBuilder content = new StringBuilder();
        for (int i = offSet; i < len; i ++) {
            String name = names[i];
            content.append(name);
            content.append(",");
        }
        return content.length() == 0 ? "" : content.substring(0, content.length()-1);
    }
}
