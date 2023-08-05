package JDBC;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class APDBUtils {
    public static void main(String[] args) {


    }

//    druid(连接池管理) + apache-dbutils(将查询结果ResultSet与Bean类完成映射) 完成查询
//    connection关闭后，ResultSet便不能再使用，因此将ResultSet的内容以Bean类的形式保存，会更加灵活
//    查询多行多列
    @Test
    public void testQueryMany() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student where id >= ?";
        List<Student> queryRes = queryRunner.query(connection, sql, new BeanListHandler<>(Student.class), 1);
        JDBCUtilsByDruid.close(null, null, connection);
        System.out.println(queryRes);
    }

//    查询单行多列
    @Test
    public void testQuerySingle () throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student where id = ?";
        Student queryRes = queryRunner.query(connection, sql, new BeanHandler<>(Student.class), 1);
        JDBCUtilsByDruid.close(null, null, connection);
        System.out.println(queryRes);
    }

//    查询单行单列
    @Test
    public void testQueryScalar () throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select name from student where id = ?";
        Object queryRes = queryRunner.query(connection, sql, new ScalarHandler<>(), 1);
        JDBCUtilsByDruid.close(null, null, connection);
        System.out.println(queryRes);
    }

//    druid + apache-dbutils 实现 DML语句
    @Test
    public void testDML () throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
////      增
//        String insertSql = "insert into student values (?, ?, ?, ?, ?)";
//        int affectedRows = queryRunner.update(connection, insertSql, 78, "李思思", 1, 2, 3);
////      删
//        String deleteSql = "delete from student where id = ?";
//        int affectedRows = queryRunner.update(connection, deleteSql, 1);
//      改
        String setSql = "update student set name = ? where id = ?";
        int affectedRows = queryRunner.update(connection, setSql, "张三三", 1);

        JDBCUtilsByDruid.close(null, null, connection);
        System.out.println(affectedRows > 0 ? "操作成功" : "执行没有影响到表");
    }


}
