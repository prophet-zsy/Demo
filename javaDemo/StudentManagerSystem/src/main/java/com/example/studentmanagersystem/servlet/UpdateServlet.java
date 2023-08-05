package com.example.studentmanagersystem.servlet;

import com.example.studentmanagersystem.DAO.StudentDAO;
import com.example.studentmanagersystem.domain.Student;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet implements getStuBeanFromReq {

    private ServletContext servletContext;
    private String configFilePath;
    private StudentDAO studentDAO;

    public void init() {
        servletContext = this.getServletContext();
        configFilePath = servletContext.getRealPath("WEB-INF/classes/config/druid.properties");
        studentDAO = new StudentDAO(configFilePath);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        准备要添加的student对象
        Student student = getStuBeanFromReq(req);
//        进行更新
        studentDAO.update(student);
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