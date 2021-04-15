package com.saybiu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  工具类使用请自行添加，并写好注释！
 */
public class Utils {
    public static String toDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
