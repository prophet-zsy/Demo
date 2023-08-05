package com.example.studentmanagersystem.servlet;

import com.example.studentmanagersystem.DAO.StudentDAO;
import com.example.studentmanagersystem.domain.Student;
import com.example.studentmanagersystem.utils.PageInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListServlet", value = "/list")
public class ListServlet extends HttpServlet {

    private ServletContext servletContext;
    private String configFilePath;
    private StudentDAO studentDAO;

    public void init() {
        servletContext = this.getServletContext();
        configFilePath = servletContext.getRealPath("WEB-INF/classes/config/druid.properties");
        studentDAO = new StudentDAO(configFilePath);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取分页
        int start = 0;
        int count = 10;
        try {
            start = Integer.parseInt(req.getParameter("pageInfo.start"));
            count = Integer.parseInt(req.getParameter("pageInfo.count"));
        } catch (Exception e) {
//            e.printStackTrace();
        }
//        查询
        List<Student> students = studentDAO.list(start, count);
        int totalCount = studentDAO.getTotalCount();

        PageInfo pageinfo = new PageInfo();
        pageinfo.setStart(start); pageinfo.setCount(count); pageinfo.setTotalCount(totalCount);
        pageinfo.setHasNext(start + count < totalCount); pageinfo.setHasPrevious(start > 0);
        pageinfo.setLast(totalCount / count * count);

        req.setAttribute("students", students);
        req.setAttribute("pageInfo", pageinfo);

//        转发重新显示页面
        try {
            req.getRequestDispatcher("listStudent.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}