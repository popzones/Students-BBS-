package com.saybiu.bbs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  工具类使用请自行添加，并写好注释！
 */
public class Utils {
    public static String toDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    public static String toDateMils(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date);
    }
    public static String pictureDate(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }
    public static String creatRandom()
    {
        Random random=new Random();


        StringBuffer stringBuffer=new StringBuffer();
        for(int x=0;x<8;x++)
        {
            int i = random.nextInt(10);
            stringBuffer.append(i);
        }
        return stringBuffer.toString();


    }

    /**
     * 判断时间是否大于60秒
     * @param firstTime
     * @param lastTime
     * @param overtime
     * @return
     */
    public static boolean overTime(String firstTime,String lastTime,long overtime){
        boolean flag=false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        try {
            one = df.parse(firstTime);
            two = df.parse(lastTime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff = time2 - time1;
            if(diff/1000>overtime){
                flag=true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
