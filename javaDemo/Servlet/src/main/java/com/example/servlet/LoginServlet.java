package com.example.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//  模拟登录页面
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // 先设置req的编码，再取内容
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        System.out.println("get>>>>>>>>>");
        System.out.println(name + password);
        if (name.equals("zhangsan") && password.equals("1234")) {
            req.setAttribute("name", name);
            req.getRequestDispatcher("welcome.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("msg", "用户名或密码错误");
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // 先设置req的编码，再取内容
        String name = req.getParameter("name");  // post 方法中的getParameter可以直接从流中读取参数
        String password = req.getParameter("password");
        System.out.println("post>>>>>>>>>");
        System.out.println(name + password);
        if (name.equals("zhangsan") && password.equals("1234")) {
            req.setAttribute("name", name);
            req.getRequestDispatcher("welcome.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("msg", "用户名或密码错误");
            resp.sendRedirect("login.jsp");
        }
    }
}
