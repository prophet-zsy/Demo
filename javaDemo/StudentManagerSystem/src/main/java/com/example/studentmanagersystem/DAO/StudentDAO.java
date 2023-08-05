package com.example.studentmanagersystem.DAO;

import com.example.studentmanagersystem.domain.Student;

import java.util.Date;
import java.util.List;

public class StudentDAO extends BasicDAO<Student>{

    public StudentDAO(String configFilePath) {
        super(configFilePath);
    }

    public int getTotalCount() {
        return ((Long) super.queryScalar("select count(*) from student")).intValue();
    }


    public boolean add(Student student) {
        return super.update("insert into student values(null, ?, ?, ?, ?, ?)",
                student.getStudentId(), student.getName(), student.getAge(),
                student.getSex(), student.getBirthday()) > 0;
    }

    public boolean delete(int id) {
        return super.update("delete from student where id = ?", id) > 0;
    }

    public boolean update(Student student) {
        return super.update("update student set studentID = ?, NAME = ?, age = ?, sex = ?, birthday = ? where id = ?",
                student.getStudentId(), student.getName(), student.getAge(),
                student.getSex(), student.getBirthday(), student.getId()) > 0;
    }

    public Student get(int id) {
        return super.querySingle("select * from student where id = ?", Student.class, id);
    }

    public List<Student> list() {
        return super.queryMulti("select * from student", Student.class);
    }
    public List<Student> list(int start, int count) {
        return super.queryMulti("select * from student limit ?, ?", Student.class, start, count);
    }
}
