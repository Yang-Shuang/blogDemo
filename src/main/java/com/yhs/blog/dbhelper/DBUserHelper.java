package com.yhs.blog.dbhelper;

import com.yhs.blog.bean.UserBean;
import com.yhs.blog.utils.LogUtil;
import com.yhs.blog.utils.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class DBUserHelper {

    private static final String USER_TABLE_NAME = "blog_users";

    /**
     * @param args where参数  如 userId=123456
     */
    public static List<UserBean> getUserData(String args) throws SQLException, InstantiationException, IllegalAccessException {
        List<UserBean> beans = null;
        Connection connection = DBConnHelper.getInstance().openConnection();
        String sql = "select * from " + USER_TABLE_NAME;
        sql = StringUtil.isEmpty(args) ? sql : (sql + " where " + args);
        LogUtil.print(sql);
        beans = DBDataHelper.query(UserBean.class, connection, sql);
        connection.close();
        return beans;
    }

    public static List<UserBean> getUserData(String userName, String password) throws SQLException, InstantiationException, IllegalAccessException {
        List<UserBean> beans = null;
        Connection connection = DBConnHelper.getInstance().openConnection();
        String sql = "select * from " + USER_TABLE_NAME;
        sql = StringUtil.isEmpty(userName) ? sql : (sql + " where userName='" + userName + "' and userPassWord='" + password + "'");
        LogUtil.print(sql);
        beans = DBDataHelper.query(UserBean.class, connection, sql);
        connection.close();
        return beans;
    }

}
