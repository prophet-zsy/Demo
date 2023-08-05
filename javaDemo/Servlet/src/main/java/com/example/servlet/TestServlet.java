package com.example.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

// 这里通过在web.xml文件配置的形式进行和url的绑定
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);  // 这句对请求进行分发，get分发给doGet，post分发给doPost；也可不写这句，直接在该函数中处理所有get与post请求
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); // 重写不要加这句，否则浏览器报错
//        获取参数
        String name = (String) req.getParameter("name");
        String age = (String) req.getParameter("age");
        System.out.println(name);
        System.out.println(age);
        System.out.println(req.getMethod()); // get or post
        System.out.println(req.getRequestURI()); // 不含主机名+端口 , /web/test
        System.out.println(req.getRequestURL()); // 含主机名+端口 , http://localhost:8080/web/test
        System.out.println(Arrays.toString(req.getCookies()));
        System.out.println(req.getQueryString());  // name=asdf&age=20
        System.out.println(req.getServletPath()); // /test
        System.out.println(req.getProtocol()); // HTTP/1.1
        System.out.println(Arrays.toString(req.getParameterValues("name"))); // [asdf], 如果QueryString为name=asdf&name=qwer&age=20, 则该方法输出[asdf, qwer]
        System.out.println(req.getContextPath()); // /web

////        返回字符串
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.println("你好你好你好你好");

////        获取输出流，用于返回文件
//        ServletOutputStream servletOutputStream = resp.getOutputStream();

////        这里是转发，只能在项目内，不能跨域，可以携带参数，共1次请求
//        req.setAttribute("name", name);
//        req.getRequestDispatcher("welcome.jsp").forward(req, resp);
////        这里是重定向，可以跨域，不能携带参数，共2次请求
//        resp.sendRedirect("http://www.baidu.com");

//        getSession(false) 是指有就返回，没有不要创建新的，默认为true ； session生命周期为客户端与服务端之间的一次会话，默认配置时间30分钟
        req.getSession().setAttribute("msg", "Hello Http Session");  // 重定向可以通过session来携带参数
        req.setAttribute("name", name);  // 重定向通过req携带的普通参数无效
        resp.sendRedirect("welcome2.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); // 重写不要加这句，否则浏览器报错
        String name = (String) req.getParameter("name");
        System.out.println(name);
    }
}
