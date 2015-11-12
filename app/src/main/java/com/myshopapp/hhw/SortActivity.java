package com.myshopapp.hhw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.myshopapp.R;
import com.myshopapp.application.YGApplication;
import com.myshopapp.hhw.adapter.SortAdapter;
import com.myshopapp.hhw.entity.Advert;
import com.myshopapp.hhw.entity.GridViewForScrollView;
import com.myshopapp.hhw.entity.Hot_Categories;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SortActivity extends AppCompatActivity {
    private GridViewForScrollView mGridView;
    private GridViewForScrollView gridView;
    private Hot_Categories sortItem;
    private List<Hot_Categories> mData;
    private List<Hot_Categories> data;
    private SortAdapter adapter;
    private SortAdapter adapterTwo;

    private ImageView ivLei;
    private ImageLoader mLoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        mGridView=(GridViewForScrollView)findViewById(R.id.gvOne);
        gridView=(GridViewForScrollView)findViewById(R.id.gvTwo);

        ivLei=(ImageView)findViewById(R.id.ivLei);
        mData=new ArrayList<Hot_Categories>();
        data=new ArrayList<Hot_Categories>();
        adapter=new SortAdapter(this,mData);
        adapterTwo=new SortAdapter(this,data);
        mGridView.setAdapter(adapter);
        gridView.setAdapter(adapterTwo);

        loadInfo();
        loadInfoTwo();
        loadImg();
        //为mGridView添加点击事件监听器
        mGridView.setOnItemClickListener(new GridViewOneItemOnClick());
        gridView.setOnItemClickListener(new GridViewTwoItemOnClick());
        ivLei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SortActivity.this,TypeOneActivity.class);
                intent.putExtra("categoryId","6519fbc142a04fe49a1788116db3b755");
                intent.putExtra("categoryName","时尚潮流");
                startActivity(intent);
            }
        });
    }

    private void loadImg() {
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/categoriesnew?pageSize=9&currentPage=1&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            JSONObject json=jsonObject.getJSONObject("categories_pictext");
                            JSONArray jsonArray=json.getJSONArray("advert");

                                JSONObject object=jsonArray.getJSONObject(0);
                                Advert advert=new Advert();
                                advert.setAdImg(object.getString("adImg"));
                                ImageLoader.ImageListener listener= ImageLoader.getImageListener(ivLei,
                                        R.drawable.cc_default_news_img, R.drawable.cc_default_news_img_fail);
                                mLoder.get(advert.getAdImg(),listener);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SortActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
    }


    //定义点击事件j监听器
    public class GridViewOneItemOnClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String level=mData.get(position).getSecondCategoryType();
            String pt=mData.get(position).getCategoryId();
            String name =mData.get(position).getCategoryName();
            Intent intent =null;
             switch (level){
                 case "1006":
                      intent =new Intent(SortActivity.this,TypeOneActivity.class);
                     intent.putExtra("categoryId",pt);
                     intent.putExtra("categoryName",name);
                     startActivity(intent);
                     break;
                 case "1004":
                     intent =new Intent(SortActivity.this,TypeTwoActivity.class);
                     intent.putExtra("categoryId",pt);
                     startActivity(intent);
                     break;
            }


        }
    }
    //定义点击事件j监听器
    public class GridViewTwoItemOnClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String level=data.get(position).getSecondCategoryType();
            String pt=data.get(position).getCategoryId();
            String name =data.get(position).getCategoryName();
            Intent intent =null;
            switch (level){
                case "1006":
                    intent =new Intent(SortActivity.this,TypeOneActivity.class);
                    intent.putExtra("categoryId",pt);
                    intent.putExtra("categoryName",name);
                    startActivity(intent);
                    break;
                case "1004":
                    intent =new Intent(SortActivity.this,TypeTwoActivity.class);
                    intent.putExtra("categoryId",pt);
                    startActivity(intent);
                    break;
            }


        }
    }
    //加载网络数据
    private void loadInfo(){
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/categoriesnew?pageSize=9&currentPage=1&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            JSONObject json=jsonObject.getJSONObject("categories_pictext");
                            JSONArray jsonArray=json.getJSONArray("hot_categories");
                            int length=jsonArray.length();
                            for(int i=0;i<length;i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                Hot_Categories sortItem=new Hot_Categories();
                                sortItem.setCategoryId(object.getString("categoryId"));
                                sortItem.setCategoryLevel(object.getString("categoryLevel"));
                                sortItem.setCategoryName(object.getString("categoryName"));
                                sortItem.setCategoryDesc(object.getString("categoryDesc"));
                                sortItem.setCategoryImg(object.getString("categoryImg"));
                                sortItem.setSecondCategoryType(object.getString("secondCategoryType"));
                                sortItem.setExtendCondition(object.getString("extendCondition"));
                                mData.add(sortItem);
                                adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SortActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
        adapter.notifyDataSetChanged();
    }
    private void loadInfoTwo() {
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/categoriesnew?pageSize=9&currentPage=1&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            JSONObject json=jsonObject.getJSONObject("categories_pictext");
                            JSONObject jso=json.getJSONObject("hot_recommend");
                            JSONArray jsonArray=jso.getJSONArray("recommends");
                            int length=jsonArray.length();
                            for(int i=0;i<length;i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                Hot_Categories sortItem=new Hot_Categories();
                                sortItem.setCategoryId(object.getString("categoryId"));
                                sortItem.setCategoryLevel(object.getString("categoryLevel"));
                                sortItem.setCategoryName(object.getString("categoryName"));
                                sortItem.setCategoryDesc(object.getString("categoryDesc"));
                                sortItem.setCategoryImg(object.getString("categoryImg"));
                                sortItem.setSecondCategoryType(object.getString("secondCategoryType"));
                                sortItem.setExtendCondition(object.getString("extendCondition"));
                                data.add(sortItem);
                                adapterTwo.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SortActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
        adapterTwo.notifyDataSetChanged();
    }

}
