package com.myshopapp.hhw.application;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

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
		super(Method.POST, url, listener, errorListener);
		initMap();
		// TODO Auto-generated constructor stub
	}
	private void initMap(){
		params=new HashMap<String, String>();
		
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return params;
	}
	public void putParams(String key,String value){
		this.params.put(key, value);
	}
}
