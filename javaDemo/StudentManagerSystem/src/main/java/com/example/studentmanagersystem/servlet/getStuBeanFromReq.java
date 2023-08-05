package com.example.studentmanagersystem.servlet;

import com.example.studentmanagersystem.domain.Student;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface getStuBeanFromReq {
    default Student getStuBeanFromReq(HttpServletRequest request) {
        Student student = new Student();
        student.setId(request.getParameter("id") == null ? null : Integer.parseInt(request.getParameter("id")));
        student.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        student.setName(request.getParameter("name"));
        student.setAge(Integer.parseInt(request.getParameter("age")));
        student.setSex(request.getParameter("sex"));
        String birthday = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        student.setBirthday(date);
        return student;
    }
}
