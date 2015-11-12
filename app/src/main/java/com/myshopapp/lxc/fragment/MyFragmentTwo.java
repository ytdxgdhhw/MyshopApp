package com.myshopapp.lxc.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.astuetz.PagerSlidingTabStrip;
import com.myshopapp.R;
import com.myshopapp.application.YGApplication;
import com.myshopapp.lxc.adapter.ViewPagerFragmentAdapter;
import com.myshopapp.lxc.dao.BrandTitle;
import com.myshopapp.lxc.dao.BrandWall;
import com.myshopapp.lxc.util.ChannelCategory;
import com.myshopapp.lxc.util.ChannelData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/10/12.
 */
public class MyFragmentTwo extends Fragment {
    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;
    private List<BrandTitle> titleList;
    private List<BrandFragment> fragments;
    private ViewPagerFragmentAdapter adapter;
    private ArrayList<BrandWall> walls;
    private String pic;

    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=LayoutInflater.from(getActivity()).inflate(R.layout.post_layout,null);
        initView();
        return v;
    }

    private void initView() {
        walls=new ArrayList<>();
        titleList=new ArrayList<>();
        fragments=new ArrayList<>();
        tabStrip=(PagerSlidingTabStrip)v.findViewById(R.id.tabStrip);
        //tabStrip.setTextSize(46);

        viewPager=(ViewPager)v.findViewById(R.id.viewPager);
        adapter=new ViewPagerFragmentAdapter(getActivity().getSupportFragmentManager(),fragments,titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                loadMessage(position);
                doAfterLoad();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        loadMessage(0);

    }
    private void doAfterLoad(){

        for (int i=0;i<titleList.size();i++){
            BrandFragment brandFragment=new BrandFragment();
            Bundle bundle=new Bundle();
            bundle.putString("pic", pic);
            bundle.putInt("url",i);
            bundle.putParcelableArrayList("walls",walls);
            brandFragment.setArguments(bundle);
            fragments.add(brandFragment);
        }
        adapter.notifyDataSetChanged();
        tabStrip.setViewPager(viewPager);
    }
    private void loadMessage(int url){
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/brandList?page="+url+"&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            titleList.clear();
                            walls.clear();
                            JSONObject jsonObject=new JSONObject(s);

                            JSONArray jsonArray=jsonObject.getJSONArray("brands_title");
                            for (int i=0;i<jsonArray.length();i++){
                                BrandTitle brandTitle=new BrandTitle();
                                brandTitle.setName(jsonArray.getJSONObject(i).getString("name"));
                                brandTitle.setId(jsonArray.getJSONObject(i).getString("id"));
                                titleList.add(brandTitle);
                            }
                            JSONArray jsonArray1=jsonObject.getJSONArray("brands_wall").getJSONObject(0).getJSONArray("value");
                            for (int i=0;i<jsonArray1.length();i++){
                                BrandWall brandWall=new BrandWall();
                                JSONObject j=jsonArray1.getJSONObject(i);
                                brandWall.setId(j.getString("id"));
                                brandWall.setName(j.getString("name"));
                                brandWall.setBrandEnglishName(j.getString("brandEnglishName"));
                                brandWall.setPic(j.getString("pic"));
                                walls.add(brandWall);
                            }
                            pic=jsonObject.getJSONArray("advert_banner").getJSONObject(0).getString("adImg");

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

}
