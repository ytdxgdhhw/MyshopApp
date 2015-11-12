package com.myshopapp.utils;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class StringPostRequest extends StringRequest {
    private Map<String,String> params;
    public StringPostRequest(int method, String url, Listener<String> listener,
                             ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        initMap();
        // TODO Auto-generated constructor stub
    }
    public StringPostRequest(String url, Listener<String> listener,
                             ErrorListener errorListener) {
        super(Request.Method.POST, url, listener, errorListener);
        initMap();
        // TODO Auto-generated constructor stub
    }
    private void initMap(){
        params=new HashMap<String, String>();

    }
    //post提交时，调用方法，获取参数
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        // TODO Auto-generated method stub
        return params;
    }
    public void putParams(String key,String value){
        this.params.put(key, value);
    }
}
