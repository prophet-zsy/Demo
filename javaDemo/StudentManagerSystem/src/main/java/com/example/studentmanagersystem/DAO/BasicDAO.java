package com.example.studentmanagersystem.DAO;

import com.example.studentmanagersystem.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//    连接池代理了connection的获得，apache-dbutils代理了Statement的工作，并增加与Bean类的映射工作
public class BasicDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    public BasicDAO(String configFilePath) {
        DBUtils.prepareDataSource(configFilePath);
    }

//    对应DML
    public int update(String sql, Object... params) {
        Connection connection = null;
        int update = -1;
        try {
            connection = DBUtils.getConnection();
            update = queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(null, null, connection);
        }
        return update;
    }

    //  查询多行多列
    public List<T> queryMulti(String sql, Class clazz, Object... params) {
        Connection connection = null;
        List<T> queryRes = null;
        try {
            connection = DBUtils.getConnection();
            queryRes = queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(null, null, connection);
        }
        return queryRes;
    }

    //    查询单行多列
    public T querySingle (String sql, Class clazz, Object... params) {
        Connection connection = null;
        T queryRes = null;
        try {
            connection = DBUtils.getConnection();
            queryRes = queryRunner.query(connection, sql, new BeanHandler<T>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(null, null, connection);
        }
        return queryRes;
    }

    //    查询单行单列
    public Object queryScalar (String sql, Object... params) {
        Connection connection = null;
        Object queryRes = null;
        try {
            connection = DBUtils.getConnection();
            queryRes = queryRunner.query(connection, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(null, null, connection);
        }
        return queryRes;
    }
}
