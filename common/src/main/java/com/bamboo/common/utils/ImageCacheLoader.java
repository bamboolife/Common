package com.bamboo.common.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.bamboo.common.utils.encryption.Md5Util;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-11 12:59
 * 描述：使用LruCache实现图片缓存加载
 */
public class ImageCacheLoader {
    private LruCache<String,Bitmap> mLruCache;

    public ImageCacheLoader(){
        //获取运行时最大内存
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        //设置最大缓存空间为运行时内存的 1/8
        int cacheSize=maxMemory/8;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //计算一个元素的缓存大小
                return super.sizeOf(key, value);
            }

        };
    }

    /**
     * 添加图片到 LruCache
     *
     * @param key
     * @param bitmap
     */
    public void addBitmap(String key, Bitmap bitmap) {
        if (getBitmap(key) == null) {
                mLruCache.put(Md5Util.getMd5String(key), bitmap);
        }
    }

    /**
     * 从缓存中获取图片
     *
     * @param key
     * @return
     */
    public Bitmap getBitmap(String key) {
        return mLruCache.get(Md5Util.getMd5String(key));
    }

    /**
     * 从缓存中删除指定的 Bitmap
     *
     * @param key
     */
    public void removeBitmapFromMemory(String key) {
        mLruCache.remove(key);
    }
}
