package com.example.studentmanagersystem.servlet;

import com.example.studentmanagersystem.DAO.StudentDAO;
import com.example.studentmanagersystem.domain.Student;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet implements getStuBeanFromReq {  // 这里没有实现该方法，直接用，使用默认实现

    private ServletContext servletContext;
    private String configFilePath;
    private StudentDAO studentDAO;

    //    init方法是在Servlet实例化之后执行的，并且只执行一次
    public void init() {
        servletContext = this.getServletContext();
        configFilePath = servletContext.getRealPath("WEB-INF/classes/config/druid.properties");
        studentDAO = new StudentDAO(configFilePath);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        准备要添加的student对象
        Student student = getStuBeanFromReq(req);
//        进行添加
        studentDAO.add(student);
//        重新显示页面
        try {
//            req.getRequestDispatcher("listStudent.jsp").forward(req, resp);
            resp.sendRedirect("/StudentManagerSystem/list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}