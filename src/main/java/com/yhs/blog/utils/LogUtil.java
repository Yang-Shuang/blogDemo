package com.yhs.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class LogUtil {

    private static final boolean isWriteLog = false;

    public static void print(Object str){
        if (str == null || (str instanceof String && str.equals("")))return;
        String log = getTimeStr() + str.toString();
        System.out.print(log);
        if (isWriteLog) {
            // 记录日志文件
        }
    }

    private static String getTimeStr(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS>>>>>>>>>>>>>>");

        return format.format(new Date());
    }
}
