package com.example.palo.beijinnews.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/22 10:39
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：网络缓存工具类
 */
public class NetCacheUtils {


    /**
     * 请求成功
     */
    public static final int SUCCESS = 1;
    /**
     * 请求失败
     */
    public static final int FAIL = 2;
    private final Handler handler;
    private final ExecutorService service;
    /**
     * 本地缓存工具类
     */
    private final LocaleCacheUtils localeCacheUtils;
    /**
     * 内存缓存工具类
     */
    private final MemoryCacheUtils memoryCacheUtils;

    public NetCacheUtils(Handler handler, LocaleCacheUtils localeCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.handler = handler;
        service = Executors.newFixedThreadPool(10);
        this.localeCacheUtils = localeCacheUtils;
        this.memoryCacheUtils = memoryCacheUtils;
    }

    public void getBitmapFromNet(String imageUrl, int position) {

//        new Thread(new MyRunnable(imageUrl,position)).start();
        service.execute(new MyRunnable(imageUrl, position));

    }

    class MyRunnable implements Runnable {

        private final String imageUrl;
        private final int position;

        public MyRunnable(String imageUrl, int position) {
            this.imageUrl = imageUrl;
            this.position = position;
        }

        @Override
        public void run() {

            //联网请求图片
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");//不能小写
                connection.setConnectTimeout(4000);
                connection.setReadTimeout(4000);
                connection.connect();
                int code = connection.getResponseCode();
                if (code == 200) {


                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    //请求网络图片，获取图片，显示到控件上
                    //向内存存一份
                    memoryCacheUtils.putBitmap(imageUrl,bitmap);
                    //向本地文件中存一份
                    localeCacheUtils.putBitmap(imageUrl,bitmap);

                    //发消息
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    msg.arg1 = position;
                    msg.what = SUCCESS;
                    handler.sendMessage(msg);

                }

            } catch (Exception e) {
                e.printStackTrace();
                Message msg = Message.obtain();
                msg.what = FAIL;
                msg.arg1 = position;
                handler.sendMessage(msg);
            }

        }
    }
}
