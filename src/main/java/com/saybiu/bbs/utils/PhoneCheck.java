package com.saybiu.bbs.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/25 1:25 上午 （日期和时间）
 */


public class PhoneCheck {

    private static final Pattern CHINA_PATTERN = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147,149
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     *
     * 中国电信号段
     * 133、149、153、173、177、180、181、189、190、191、193、199
     * 中国联通号段
     * 130、131、132、145、155、156、166、167、171、175、176、185、186、196
     * 中国移动号段
     * 134(0-8)、135、136、137、138、139、1440、147、148、150、151、152、157、158、159、172、178、182、183、184、187、188、195 [1]  、197、198
     * 中国广电号段
     * 192
     * 其他号段
     * 14号段部分为上网卡专属号段：中国联通145，中国移动147，中国电信149.
     * 虚拟运营商：
     * 电信：1700、1701、1702、162
     * 移动：1703、1705、1706、165
     * 联通：1704、1707、1708、1709、171、167
     * 卫星通信：1349、174
     * 物联网：140、141、144、146、148
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        Matcher m = CHINA_PATTERN.matcher(str);
        return m.matches();
    }


}
