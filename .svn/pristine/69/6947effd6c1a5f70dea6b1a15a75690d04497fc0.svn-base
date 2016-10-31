/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

package com.example.palo.beijinnews.volley;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 自定义的类
 */
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
	public BitmapLruCache(int maxSize) {
		super(maxSize);
	}

	/**
	 * 计算每张图片的大小
	 * @param key
	 * @param bitmap
	 * @return
	 */
	@Override
	protected int sizeOf(String key, Bitmap bitmap) {
		return bitmap.getRowBytes() * bitmap.getHeight();
	}

	/**
	 *从集合中得到缓存的图片
	 * @param url
	 * @return
	 */
	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	/**
	 * 往集合中添加图片
	 * @param url
	 * @param bitmap
	 */
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}
}
