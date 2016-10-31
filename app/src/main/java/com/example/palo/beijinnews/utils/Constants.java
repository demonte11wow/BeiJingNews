package com.example.palo.beijinnews.utils;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/17 10:17
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：联网请求的配置
 */
public class Constants {
    public static final int mMinValue =1;
    public static final int mMaxValue = 10;

    /**
     * 请求网络的公共连接地址
     */
    public static final String BASE_URL = "http://192.168.43.42:8080/web_home";


    /**
     * 新闻中心联网请求地址
     */
    public static final String NEWS_CENTER_URL = BASE_URL+"/static/api/news/categories.json";

    /**
     * 商品热卖
     */
    public static final String WARES_HOT_URL = "http://112.124.22.238:8081/course_api/wares/hot?";


}
