package com.myshopapp.lxc.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myshopapp.R;
import com.myshopapp.application.YGApplication;
import com.myshopapp.hhw.entity.GridViewForScrollView;
import com.myshopapp.lxc.adapter.BrandAdapter;
import com.myshopapp.lxc.dao.BrandTitle;
import com.myshopapp.lxc.dao.BrandWall;
import com.myshopapp.utils.ImageLoaderUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/10/12.
 */
public class BrandFragment extends Fragment {
    private ArrayList<BrandWall> list;
    private GridViewForScrollView  gridViewForScrollView;
    private View v;
    private BrandAdapter adapter;
    private String pic;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (v==null){
            v=LayoutInflater.from(getActivity()).inflate(R.layout.brandfragment,null);
            initView();
        }

        return v;
    }
    private void initView(){
        list=new ArrayList<>();
        gridViewForScrollView=(GridViewForScrollView)v.findViewById(R.id.gridView);
        gridViewForScrollView.setFocusable(false);
        gridViewForScrollView.setOnItemClickListener(new MyListener());
        adapter=new BrandAdapter(list,getActivity());
        gridViewForScrollView.setAdapter(adapter);
        loadMessage(url);
    }
    private void doAfterLoad(){
        adapter.notifyDataSetChanged();
    }
    private void loadMessage(int url){
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/brandList?page="+url+"&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            list.clear();

                            JSONObject jsonObject=new JSONObject(s);

                            JSONArray jsonArray1=jsonObject.getJSONArray("brands_wall").getJSONObject(0).getJSONArray("value");
                            for (int i=0;i<jsonArray1.length();i++){
                                BrandWall brandWall=new BrandWall();
                                JSONObject j=jsonArray1.getJSONObject(i);
                                brandWall.setId(j.getString("id"));
                                brandWall.setName(j.getString("name"));
                                brandWall.setBrandEnglishName(j.getString("brandEnglishName"));
                                brandWall.setPic(j.getString("pic"));
                                list.add(brandWall);
                            }
                            pic=jsonObject.getJSONArray("advert_banner").getJSONObject(0).getString("adImg");
                            ImageLoaderUtil.display(pic,(ImageView)v.findViewById(R.id.img));
                            doAfterLoad();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        YGApplication.getInstance().getRequestQueue().add(request);
    }
    private class MyListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
    private int url;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

        url=args.getInt("url");
    }
}
