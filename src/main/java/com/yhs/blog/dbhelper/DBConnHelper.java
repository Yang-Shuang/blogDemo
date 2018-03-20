package com.yhs.blog.dbhelper;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class DBConnHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/blogDemo?useUnicode=true&characterEncoding=UTF-8";

    private Connection conn;

    public Connection openConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("成功加载MySQL驱动程序");
                conn = DriverManager.getConnection(DB_URL, "root", "123456");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    private DBConnHelper(){}

    public static DBConnHelper getInstance(){
        return new DBConnHelper();
    }

    public void close(){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库异常");
                e.printStackTrace();
            }
        }
    }
}
