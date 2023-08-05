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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet implements getStuBeanFromReq {

    private ServletContext servletContext;
    private String configFilePath;
    private StudentDAO studentDAO;

    public void init() {
        servletContext = this.getServletContext();
        configFilePath = servletContext.getRealPath("WEB-INF/classes/config/druid.properties");
        studentDAO = new StudentDAO(configFilePath);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        准备要修改的student对象
        Student student = studentDAO.get(Integer.parseInt(req.getParameter("id")));
//        显示修改页面
        req.setAttribute("student", student);
        try {
            req.getRequestDispatcher("editStudent.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}