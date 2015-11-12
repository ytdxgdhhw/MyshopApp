package com.myshopapp.hhw.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;


public class CCApplication extends Application{
	private RequestQueue requestQueue;
	public static CCApplication application;
	private CCImageCache mCache;
//	private CCUser cCUser;//��½�û�
//	public CCUser getUser() {
//		return cCUser;
//	}
//	public void setUser(CCUser cCUser) {
//		this.cCUser = cCUser;
//	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application=this;
		requestQueue= Volley.newRequestQueue(this);
		mCache=new CCImageCache();
		 
	}
	public static CCApplication getInstance(){
		return application;
	}
	public RequestQueue getRequestQueue(){
		return this.requestQueue;
	}
	public ImageCache getImageCache(){
		return this.mCache;
	}
}
