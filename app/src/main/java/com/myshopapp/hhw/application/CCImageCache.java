package com.myshopapp.hhw.application;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class CCImageCache implements ImageCache {
	private int maxSize = 1024 * 1024 * 10;
	private LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(
			maxSize) {

		@Override
		protected int sizeOf(String key, Bitmap value) {
			// TODO Auto-generated method stub
			return value.getRowBytes()*value.getHeight();
		}
	};

	@Override
	public Bitmap getBitmap(String arg0) {
		// TODO Auto-generated method stub
		return this.mCache.get(arg0);
	}

	@Override
	public void putBitmap(String arg0, Bitmap arg1) {
		// TODO Auto-generated method stub
		this.mCache.put(arg0, arg1);
	}

}
