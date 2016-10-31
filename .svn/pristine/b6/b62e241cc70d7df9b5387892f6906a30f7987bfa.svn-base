package com.example.palo.beijinnews.utils;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/22 10:36
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：图片三级缓存工具类
 */
public class BitmapCacheUtils {

    /**
     * 网络缓存
     */
    private NetCacheUtils netCacheUtils;

    /**
     * 本地缓存
     */
    private LocaleCacheUtils localeCacheUtils;

    /**
     * 内存缓存
     */
    private MemoryCacheUtils memoryCacheUtils;


    public BitmapCacheUtils(Handler handler) {
        memoryCacheUtils = new MemoryCacheUtils();
        localeCacheUtils = new LocaleCacheUtils(memoryCacheUtils);
        netCacheUtils = new NetCacheUtils(handler, localeCacheUtils,memoryCacheUtils);

    }


    /**
     * 三级缓存设计步骤：
     *   * 从内存中取图片
     *   * 从本地文件中取图片
     *        向内存中保持一份
     *   * 请求网络图片，获取图片，显示到控件上
     *      * 向内存存一份
     *      * 向本地文件中存一份
     *
     * @param imageUrl
     * @param position
     * @return
     */
    public Bitmap getBitmap(String imageUrl, int position) {

        //从内存中取图片
        if (memoryCacheUtils != null) {
            Bitmap bitmap = memoryCacheUtils.getBitmap(imageUrl);
            if (bitmap != null) {
                //从内存获取的图片
                LogUtil.e("从内存获取的图片=="+position);
                return  bitmap;
            }
        }
        //从本地文件中取图片
        if (localeCacheUtils != null) {
            Bitmap bitmap = localeCacheUtils.getBitmap(imageUrl);
            if (bitmap != null) {
                //从本地获取的图片
                LogUtil.e("从本地获取的图片=="+position);
                return  bitmap;
            }
        }
        //请求网络图片，获取图片，显示到控件上
        if (netCacheUtils != null) {
            netCacheUtils.getBitmapFromNet(imageUrl, position);
        }

        return null;
    }
}
