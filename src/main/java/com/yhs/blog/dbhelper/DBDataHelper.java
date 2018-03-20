package com.yhs.blog.dbhelper;

import com.yhs.blog.utils.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class DBDataHelper {

    public static <T> long insert(List<T> datas,Connection conn, String tables) {
        return 0;
    }

    public static long delete(Connection conn,String tables,String args) {
        return 0;
    }

    public static long update(Connection conn,String sql){
        return 0;
    }

    public static <T> List<T> query(Class<T> c,Connection conn, String sql) throws SQLException,IllegalAccessException,InstantiationException{
        List<T> ts = null;
        ResultSet set = null;
        Statement stmt = null;
        stmt = conn.createStatement();
        set = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
        if (set == null) {
            return null;
        }

        ts = new ArrayList<T>();
        HashMap<String, Method> methodNames = new HashMap<String, Method>();
        T t = null;
        t = c.newInstance();
        Method[] methods = c.getMethods();
        for (int j = 0; j < methods.length; j++) {
            String methodname = methods[j].getName();
            if (methodname.contains("set")) {
                methodNames.put(tofirstLowerCase(methodname.substring(3, methodname.length())), methods[j]);
            }
        }
        while (set.next()) {
            T bb = c.newInstance();
            for (Map.Entry<String, Method> entry : methodNames.entrySet()) {
                Object s = set.getObject(entry.getKey());
                try {
                    if (s != null) {
                        Field field = c.getDeclaredField(entry.getKey());
                        Type type = field.getGenericType();
                        Class subbeanclass = (Class) type;
                        entry.getValue().invoke(bb, s);
                    }
                }catch (NoSuchFieldException e){
                    LogUtil.print("NoSuchFieldException---" + entry.getKey());
                }catch (InvocationTargetException e){
                    LogUtil.print("InvocationTargetException---" + entry.getKey());
                }

            }
            ts.add(bb);
        }
        return ts;
    }

    /**
     * 将字符串的首字符转化成小写
     *
     * @param str 要转化的字符串
     * @return 返回首字符变小写之后的字符串
     */
    public static String tofirstLowerCase(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
        } else {
            return str;
        }
    }


}
