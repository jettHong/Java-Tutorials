package com.jett.java.jdbc;

import org.apache.commons.compress.utils.Iterators;

import java.sql.*;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * JDBC 基本演示
 * Q：为什么要 Class.forName
 * Q：PreparedStatement、Statement，两者有什么差别
 * Q：如何开关事务。
 * Q：如何防止SQL注入
 * Q：大文本取得，二进制类型？？？
 * Q：存储过程调用？？？
 *   call
 * Q：函数调用？？？
 * Q：违背“双亲委托加载模型”
 * Q：jdbc规范使主要涉及的设计模式：
 *  DriverManager：桥接模式。桥接模式（Bridge)是一种结构型设计模式。Bridge模式基于类的最小设计原则，
 *  通过使用封装、聚合及继承等行为让不同的类承担不同的职责（单一职责体系）。
 *  它的主要特点是把抽象(Abstraction)与行为实现(Implementation)分离开来，
 *  从而可以保持各部分的独立性以及应对他们各自的功能扩展。
 * Q：JDCB缺点。=> 数据库连接池 => Spring JDBCTemplate => MyBatis
 *    1、步骤繁琐（整体操作、点位符设置，取得结果、转换结果等）
 *    2、需要手动维护重要资源 Connection
 *    3、SQL语句硬编码
 * TODO：元数据
 *
 * REF：
 * 深入理解JDBC设计模式: DriverManager 解析
 * https://www.cnblogs.com/yougewe/p/12460685.html
 * JDBC驱动加载机制（SPI,通过 java 内置的工具类 java.util.ServiceLoader 实现，其它应用场景，apache-common-logging）
 * https://blog.csdn.net/buqutianya/article/details/78936947
 * JDCB缺点与优化思路
 * https://www.processon.com/view/5fdb21f2e0b34d66b8183045
 * Spring jdbc 时序图
 * https://www.processon.com/view/5e8324dce4b0a2d870240f00
 *
 * @author jett
 */
public class JdbcTest {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 抛出 ClassNotFoundException
        // MySQL：The new driver class is `com.mysql.cj.jdbc.Driver'.
        //        The driver is automatically registered via the SPI and manual loading of
        //        the driver class is generally unnecessary.
        //        随着SPI机制的引入，已经不需要在手动 Class.forName。
        //        https://www.processon.com/view/5fab8ff507912951dc69bb54
        // Q：为什么要 Class.forName，可以从下面的 getConnection 得到答案。
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        String url = "jdbc:mysql://127.0.0.1:3308/blue?characterEncoding=UTF-8&useSSL=false&useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        
        // 抛出 SQLException，这个异常是整个JDBC相关最大的异常，参见 SQLException.png
        // 一、取得类加载器，用于下面判断是否注册到当前类加载器上面。（不明白）
        // 1、sun.reflect.Reflection.getCallerClass().getClassLoader() 取得当前线程的类加载器，native 修饰。
        // 2、取不到再 Thread.currentThread().getContextClassLoader()
        // 二、循环 registeredDrivers，分别试能否取得连接对象 Connection。
        // for(DriverInfo aDriver : java.sql.DriverManager.registeredDrivers) {
        //     Connection con = aDriver.driver.connect(url, info);
        // }
        Connection connection = DriverManager.getConnection(url, user, password);
        
        // 事务是否自动提交，false=需要手动 connection.commit() 才提交。
        connection.setAutoCommit(false);
        // SQL 语句被预编译并存储在PreparedStatement对象中。 然后可以使用此对象多次有效地执行此语句。
        PreparedStatement preparedStatement = connection.prepareStatement("update t_big t set t.gname = '年年有今日' where t.id = ?");
        // tip: setXxx(index, obj) 设置不同数据类型，index 从 1 开始。
        preparedStatement.setInt(1, 1);
        int affected = preparedStatement.executeUpdate();
        System.out.println("影响的行数是：" + affected);
        // 提交事务
        connection.commit();
        // 回滚事务
        // connection.rollback();
    
        // 事务是否自动提交，true=每执行一行数据就提交，类似 navicat 里面执行 mysql 语句。
        connection.setAutoCommit(true);
        
        // 查询 => 取得 resultSet 结果集
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_big where id = '1'");
        while (resultSet.next()) {
            // 取得结果集有两种方法：columnIndex、columnLabel
            // tip：columnIndex 从 1 开始，columnLabel 需要区分大小写。
            System.out.println("索引从 1 开始" + resultSet.getInt(1));
            String gname = resultSet.getString("gname");
            System.out.println("gname：" + gname);
        }
        // Q：interface PreparedStatement extends Statement，两者有什么差别
        
        // 批量执行
        PreparedStatement batchPs = connection.prepareStatement("update t_big t set t.gname = '批量有今朝' where t.id = ?");
        IntStream.range(2, 5).forEach(i -> {
            try {
                batchPs.setInt(1, i);
                // 添加到预处理队列中
                batchPs.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] batchResult = batchPs.executeBatch();
        connection.commit();
        System.out.println("批量执行结果：" + Arrays.toString(batchResult));
        
        
        // 依次关闭所有连接，很重要！！！所以一般地都放在 try...finally{ 这里关闭 }
        resultSet.close();
        statement.close();
        preparedStatement.close();
        connection.close();
        
    }
    
}
