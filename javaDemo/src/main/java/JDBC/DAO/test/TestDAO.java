package JDBC.DAO.test;

import JDBC.DAO.dao.StudentDAO;
import JDBC.Student;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

// todo 不建议sql语句上到业务层，sql语句只停留在DAO层就好
public class TestDAO {

    @Test
    public void testStudentDAO() throws SQLException {
        StudentDAO studentDAO = new StudentDAO();

//        查询多行多列
        String sql = "select * from student where id >= ?";
        List<Student> students = studentDAO.queryMulti(sql, Student.class, 3);
        System.out.println(students);

//        如果查询多行单列，或不满足所有列，使用多行多列的查询，缺少的属性默认为null

//        查询单行多列
        sql = "select * from student where id = ?";
        Student student = studentDAO.querySingle(sql, Student.class, 3);
        System.out.println(student);


//        查询单行单列
        sql = "select name from student where id = ?";
        Object object = studentDAO.queryScalar(sql, 3);
        System.out.println(object);


//       DML
        sql = "insert into student values (?, ?, ?, ?, ?)";
        int accfectedRows = studentDAO.update(sql, 79, "王呜呜", 1, 2, 3);
        System.out.println(accfectedRows > 0 ? "操作成功" : "操作对表无影响");

        sql = "delete from student where id = ?";
        accfectedRows = studentDAO.update(sql, 78);
        System.out.println(accfectedRows > 0 ? "操作成功" : "操作对表无影响");

        sql = "update student set name = ? where id = ?";
        accfectedRows = studentDAO.update(sql, "赵四四", 79);
        System.out.println(accfectedRows > 0 ? "操作成功" : "操作对表无影响");
    }
}
