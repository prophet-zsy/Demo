package JDBC;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import javax.sql.DataSource;

public class JDBCInit {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        准备一下登录用的用户名和密码
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234qwer");

//        这里使用mysql对应的驱动，将其jar包下载下来放到libs文件夹下，add as library

        /** url注释
         * jdbc:mysql是协议
         * localhost是服务器ip
         * 3306是mysql监听的端口
         * db01是具体哪个数据库
         */
//        获取连接方式1
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01", properties);
//        获取连接方式2
        Driver driver = new Driver();
        Connection connection = driver.connect("jdbc:mysql://localhost:3306/db01", properties);

//        String sql = "create table hhh (id int, name varchar(32))";
        String sql = "insert into hhh values (3, 'zhao')";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql); // 如果执行的是dml语句，使用executeUpdate，返回的是受影响的行数 ； 如果执行的是create语句，使用execute，返回的是布尔值
        System.out.println(rows > 0 ? "sql执行成功" : "sql执行失败");
        statement.close();
        connection.close();
    }

    /**
     * 以下5种连接数据库的方式
     */

    @Test
    public void connect1() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234qwer");

        Driver driver = new Driver();
        Connection connection = driver.connect("jdbc:mysql://localhost:3306/db01", properties);

        System.out.println("mysql连接成功");
    }

    @Test
    public void connect2() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234qwer");

        Class<?> DriverC = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) DriverC.newInstance();
        Connection connection = driver.connect("jdbc:mysql://localhost:3306/db01", properties);

        System.out.println("mysql连接成功");
    }

    @Test
    public void connect3() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234qwer");
//或
        String user = "root";
        String password = "1234qwer";
        String url = "jdbc:mysql://localhost:3306/db01";

        Class<?> DriverC = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) DriverC.newInstance();
        DriverManager.registerDriver(driver);
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01", properties);
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("mysql连接成功");
    }

    @Test
    public void connect4() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String user = "root";
        String password = "1234qwer";
        String url = "jdbc:mysql://localhost:3306/db01";

        /*  com.mysql.cj.jdbc.Driver类加载时，在静态代码中进行了注册
         static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
         */

//        高版本的driver不写这句话也可以，因为它回自动调用驱动jar包下meta-inf/services/java.sql.Driver文本中的类名去注册
        Class<?> DriverC = Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("mysql连接成功");
    }

    @Test
    public void connect5() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
//        读取配置文件进行连接
        Properties properties = new Properties();
        properties.load(new FileInputStream("mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        DriverManager.getConnection(url, user, password);

        System.out.println("mysql连接成功");
    }

    /*
    statement.execute执行创建表
    statement.executeUpdate执行增删改
    statement.executeQuery执行查询
    resultSet.next()将当前游标后移，并判断还有无数据，如果有返回true，否则返回false
    resultSet.previous()将当前游标前移，并判断还有无数据，如果有返回true，否则返回false
     */
    @Test
    public void practise() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        statement.execute("create table news1 (id int, name varchar(32))");
//        insert、update、delete
        for (int i = 0; i < 5; i++) {
            int rows = statement.executeUpdate("insert into news1 values (" + String.valueOf(i + 1) + ", 'zhang')");
        }
        int rows = statement.executeUpdate("update news1 set name = 'zhao' where id = 1");
        rows = statement.executeUpdate("delete from news1 where id = 3");
//        select
        ResultSet resultSet = statement.executeQuery("select * from news1");
        while (resultSet.next()) {
//            通过列名获得值
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            或通过列的编号获得值
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println(id + "\t" + name);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void sqlInjection() throws SQLException, ClassNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

//        查询到zhangsan且密码正确，则登录成功
//        String name = "zhangsan";
//        String psd = "1234qwer";
//        但是如果输入这样的字符串，也会登录成功，即为sql注入
        String name = "zhang";
        String psd = "' or 'hhh' = 'hhh";  //这里在where子句里加了or真的逻辑，相当于执行了select *
        ResultSet resultSet = statement.executeQuery("select * from admin1 where name = '" + name + "' and password = '" + psd + "'");
        if (resultSet.next()) {
            System.out.println("当前登录成功");
        } else {
            System.out.println("当前登录失败");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    /*
    可以避免sql注入
    preparedStatement和satement执行sql语句的api相同，同样包含以下三种，只是用法稍不同，使用占位符"?"代替，然后再填写"?"当中的内容
    preparedStatement.execute
    preparedStatement.executeQuery
    preparedStatement.executeUpdate
     */
    @Test
    public void preparedStatement() throws SQLException, ClassNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "select * from admin1 where name = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, "zhangsan");
//        preparedStatement.setString(2, "1234qwer");
//        下面的字符串不会再注入成功
        preparedStatement.setString(1, "zhang");
        preparedStatement.setString(2, "' or 'hhh' = 'hhh");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("当前登录成功");
        } else {
            System.out.println("当前登录失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void practisePreparedStatement() throws SQLException, ClassNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "create table if not exists admin2 (id int, name varchar(32))";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        这里的res表示是否有结果返回，而不是代表语句是否执行成功
        boolean res = preparedStatement.execute();
        System.out.println("创建成功");

        int rows = 0;
        for (int i = 0; i < 5; i++) {
            sql = "insert into admin2 values(?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, i + 1);
            preparedStatement.setString(2, "zhang");
            rows += preparedStatement.executeUpdate();
        }
        System.out.println(rows == 5 ? "插入成功" : "插入失败");

        sql = "update admin2 set name = ? where id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "zhao");
        preparedStatement.setInt(2, 3);
        rows = preparedStatement.executeUpdate();
        System.out.println(rows == 1 ? "修改成功" : "修改失败");

        sql = "delete from admin2 where id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        rows = preparedStatement.executeUpdate();
        System.out.println(rows == 1 ? "删除成功" : "删除失败");

        sql = "select * from admin2";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(id + "\t" + name);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void JDBCUse() {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "insert into admin2 values(3, 'zhang')";
            preparedStatement = connection.prepareStatement(sql);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows == 1 ? "插入成功" : "插入失败");

            sql = "select * from admin2";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                System.out.println(id + "\t" + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
    }

    @Test
    public void transactionUse() {
//        使用事务来处理转账
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            创建表格
            String createSql1 = "DROP TABLE IF EXISTS `account`";
            String createSql2 = "CREATE TABLE IF NOT EXISTS `account` " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT," +
                    "`name` CHAR(20) NOT NULL DEFAULT ''," +
                    "banlance DOUBLE NOT NULL DEFAULT 0" +
                    ")";
            preparedStatement = connection.prepareStatement(createSql1);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(createSql2);
            preparedStatement.execute();
//            插入转账前数据
            String insertSql = "INSERT INTO `account` VALUES(NULL, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, "马云");
            preparedStatement.setInt(2, 3000);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows == 1 ? "插入成功" : "插入失败");
            preparedStatement.setString(1, "马化腾");
            preparedStatement.setInt(2, 10000);
            rows = preparedStatement.executeUpdate();
            System.out.println(rows == 1 ? "插入成功" : "插入失败");

            try {
                //            开始转账
                connection.setAutoCommit(false);  // 开始一个事务
                String fromSql = "UPDATE `account` SET banlance = banlance - 100 WHERE `name` = '马云'";
                String toSql = "UPDATE `account` SET banlance = banlance + 100 WHERE `name` = '马化腾'";
                preparedStatement = connection.prepareStatement(fromSql);
                preparedStatement.executeUpdate();
//                int a = 1/0; // 触发异常进行回滚
                preparedStatement = connection.prepareStatement(toSql);
                preparedStatement.executeUpdate();
                connection.commit();  // 最后提交
                System.out.println("转账成功");
            } catch (Exception e) {
                connection.rollback(); // 如果失败，进行回滚
                e.printStackTrace();
                System.out.println("转账失败");
            }

            String resSql = "select * from `account`";
            preparedStatement = connection.prepareStatement(resSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double banlance = resultSet.getDouble(3);
                System.out.println(id + "\t" + name + "\t" + banlance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
    }

    //    批处理
    @Test
    public void batchSql() {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "insert into admin2 values(?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 5000; i++) {
                preparedStatement.setInt(1, 3);
                preparedStatement.setString(2, "zhangsan");
                int rows = preparedStatement.executeUpdate();
            }
            long end = System.currentTimeMillis();
            System.out.println("普通插入用时" + (end - start) + "ms");

            start = System.currentTimeMillis();
            for (int i = 0; i < 5000; i++) {
                preparedStatement.setInt(1, 3);
                preparedStatement.setString(2, "zhangsan");
                preparedStatement.addBatch();
                if ((i + 1) % 1000 == 0) {
                    int rows = preparedStatement.executeUpdate();
                    preparedStatement.clearBatch();
                }
            }
            end = System.currentTimeMillis();
            System.out.println("批处理插入用时" + (end - start) + "ms");

            sql = "select count(*) from admin2";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int num = resultSet.getInt(1);
                System.out.println(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
    }


    //    不使用数据库连接池，进行5000次连接，进行测试
    @Test
    public void withoutPool() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = JDBCUtils.getConnection();
            JDBCUtils.close(null, null, connection);
        }
        long end = System.currentTimeMillis();
        System.out.println("不使用连接池 5000次连接耗时" + (end - start));
    }

    //    数据库连接池C3P0(使用代码进行配置)，老牌工具，比较稳定
    @Test
    public void c3p0Use1() throws IOException, PropertyVetoException, SQLException {
//        载入配置
        Properties properties = new Properties();
        properties.load(new FileInputStream("./mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl(url);

        comboPooledDataSource.setInitialPoolSize(10);
        comboPooledDataSource.setMaxPoolSize(50);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0 5000次连接耗时" + (end - start));
    }

    //    数据库连接池C3P0(使用配置文件进行配置)，老牌工具，比较稳定
    @Test
    public void c3p0Use2() throws IOException, PropertyVetoException, SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("c3p0-config"); // c3p0-config配置文件的位置放在src下面
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("c3p0 5000次连接耗时" + (end - start));
    }

    //    数据库连接池druid，阿里提供的工具，比较稳定，且比较快(大量连接时优势比较明显)，目前最常用
    //    druid同样有两种使用方式（代码配置、文件配置），这里展示文件配置
    @Test
    public void druidUse() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("./src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("druid 5000次连接耗时" + (end - start));
    }

//    可以将JDBCUtils改装为使用连接池的方式
    @Test
    public void JDBCUtilsBydruidUse() {
        Connection connection = JDBCUtilsByDruid.getConnection();
        JDBCUtilsByDruid.close(null, null, connection);
    }

}
