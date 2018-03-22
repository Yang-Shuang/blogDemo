package com.yhs.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YangShuang
 * on 2018/3/20.
 */
public class LogUtil {

    private static final boolean isWriteLog = true;
    private static final String LOG_FILE_PATH = "E:\\tomcatlog/";
    public static void print(Object str){
        if (str == null || (str instanceof String && str.equals("")))return;
        String log = getTimeStr() + str.toString();
        System.out.println(log);
        if (isWriteLog) {
            writeLog(log + "\n");
        }
    }

    private static String getTimeStr(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS----->");
        return format.format(new Date());
    }

    private static void writeLog(String log){
        String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        FileUtil.writeFile(LOG_FILE_PATH + fileName + ".log",log,true);
    }


}
