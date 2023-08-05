package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//cookie存在于客户端，session存在于服务端，cookie相当于服务端给客户端分配的凭证，用于区分区分不同客户端
//例如可以用cookie实现七天免登录
@WebServlet("/cookie")
public class HelloCookie extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        创建cookie
        Cookie cookie = new Cookie("age", "56");
//        设置存活时间，默认存活周期为一个session   // 一般cookie中不传中文，如果传中文，则需要转码
        cookie.setMaxAge(1*24*60*60);  // 1天，如果该值<0，则为瞬时cookie，默认一个session；如果该值=0，则该cookie作废
//        绑定响应路径，如果请求路径中包含该cookie绑定的路径，则响应会携带该cookie （这里存疑，如果在其他路径的请求下也可以携带该cookie的话，那该cookie应如何保证它不被回收，需要实验验证下）
        cookie.setPath("/");
//        将cookie添加到响应中
        resp.addCookie(cookie);
    }
}
