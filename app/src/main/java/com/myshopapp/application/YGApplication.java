package com.myshopapp.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2015/10/12.
 */
public class YGApplication extends Application{
    private RequestQueue requestQueue;
    private static YGApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        requestQueue= Volley.newRequestQueue(this);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
    }
    public RequestQueue getRequestQueue(){
        return this.requestQueue;
    }
    public static YGApplication getInstance(){
        return application;
    }
}
