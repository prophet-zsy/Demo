package com.example.studentmanagersystem.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;


public class DBUtils {
    private static DataSource dataSource = null; // 单例

    public static boolean prepareDataSource(String configFilePath) {
//        System.out.println("程序运行路径： " + System.getProperty("user.dir"));
//        System.out.println("文件路径： " + configFilePath);
        if (dataSource == null) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(configFilePath));
                dataSource = DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public static Connection getConnection() {
        try {
            if (dataSource == null) throw new Exception("You should prepare the dataSource before using it");
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        String configFilePath = "./StudentManagerSystem/src/main/resources/config/druid.properties";
        prepareDataSource(configFilePath);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("show tables");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String string = resultSet.getString(1);
            System.out.println(string);
        }
        close(null, null, connection);
    }
}


