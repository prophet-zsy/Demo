package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie2")
public class HelloCookie2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies) {
            System.out.println(cookie.getName() + "--->" + cookie.getValue());
        }
    }
}
