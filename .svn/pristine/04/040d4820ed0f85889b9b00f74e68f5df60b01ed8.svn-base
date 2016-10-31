package com.example.palo.beijinnews.utils;

import android.graphics.Bitmap;

import org.xutils.cache.LruCache;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/22 14:16
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：图片缓存工具类
 */
public class MemoryCacheUtils {
    private LruCache<String, Bitmap> lruCache;

    public MemoryCacheUtils() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            //计算每张图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return (value.getRowBytes() * value.getHeight()) / 1024;
            }
        };
    }

    /**
     * 根据Url添加图片到内存中
     * @param imageUrl
     * @param bitmap
     */
    public void putBitmap(String imageUrl, Bitmap bitmap) {
        lruCache.put(imageUrl,bitmap);
    }

    /**
     * 根据Url从内存中获取图片
     * @param imageUrl
     * @return
     */
    public Bitmap getBitmap(String imageUrl) {
        return lruCache.get(imageUrl);
    }
}
