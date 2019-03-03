package dev.app.flikit.data;

import android.graphics.Bitmap;
import android.util.LruCache;

public class BitmapCache extends LruCache<String, Bitmap> {

    private static BitmapCache cache;

    public BitmapCache(int size)
    {
        super(size);
    }

    private BitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    public static BitmapCache getInstance() {
        if (cache == null) {
            cache = new BitmapCache();
        }
        return cache;
    }

    @Override
    protected int sizeOf(String key, Bitmap bitmap)
    {
        return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
    }

    public Bitmap getBitmap(String url)
    {
        return get(url);
    }

    public void putBitmap(String url, Bitmap bitmap)
    {
        put(url, bitmap);
    }

}
