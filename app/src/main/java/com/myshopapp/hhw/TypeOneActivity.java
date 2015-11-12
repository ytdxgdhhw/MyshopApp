package com.myshopapp.hhw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myshopapp.R;
import com.myshopapp.application.YGApplication;
import com.myshopapp.hhw.adapter.TypeOneAdapter;
import com.myshopapp.hhw.adapter.TypeTwoAdapter;
import com.myshopapp.hhw.entity.Brands;
import com.myshopapp.hhw.entity.GridViewForScrollView;
import com.myshopapp.hhw.entity.Secondcategories;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TypeOneActivity extends AppCompatActivity {
    private TypeOneAdapter adapterOne;
    private TypeTwoAdapter adapterTwo;

    private List<Brands> mData;
    private List<Secondcategories> data;
    private GridViewForScrollView gvPinpai;
    private String url;
    private TextView   tvTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_one);

        tvTop=(TextView)findViewById(R.id.tvTop);
        mData = new ArrayList<Brands>();
        data=new ArrayList<Secondcategories>();
        gvPinpai = (GridViewForScrollView) findViewById(R.id.gvPinpai);
        adapterOne = new TypeOneAdapter(this, mData);
        adapterTwo=new TypeTwoAdapter(this,data);
        String name=getIntent().getStringExtra("categoryName");
        tvTop.setText(name);
        url = getIntent().getStringExtra("categoryId");
        loadDataOne();

    }
    public class GridViewOneItemOnClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String pt=data.get(position).getCategoryId();
            Intent intent =null;
                    intent =new Intent(TypeOneActivity.this,TypeTwoActivity.class);
                    intent.putExtra("categoryId",pt);
                    startActivity(intent);
        }
    }
    public class GridViewTwoItemOnClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String pt=mData.get(position).getBrandId();
            Intent intent =null;
            intent =new Intent(TypeOneActivity.this,TypeTwoActivity.class);
            intent.putExtra("brandId",pt);
            startActivity(intent);
        }
    }
    private void loadDataOne() {
        StringRequest request = new StringRequest("http://mobile.yougou.com/v_1.8/secondcategories?firstcCtegoryId=" + url + "&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getJSONArray("secondcategories").length() == 0) {
                                gvPinpai.setAdapter(adapterOne);
                                JSONArray jsonArray = jsonObject.getJSONArray("brands");
                                int length = jsonArray.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    Brands brands = new Brands();
                                    brands.setBrandId(object.getString("brandId"));
                                    brands.setBrandImg(object.getString("brandImg"));
                                    mData.add(brands);
                                    adapterOne.notifyDataSetChanged();
                                    gvPinpai.setOnItemClickListener(new GridViewTwoItemOnClick());
                                }
                            }else{
                                gvPinpai.setAdapter(adapterTwo);
                                JSONArray jsonArray = jsonObject.getJSONArray("secondcategories");
                                int length =jsonArray.length();
                                for(int i=0;i<length;i++){
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    Secondcategories secondcategories=new Secondcategories();
                                    secondcategories.setCategoryId(object.getString("categoryId"));
                                    secondcategories.setCategoryName(object.getString("categoryName"));
                                    secondcategories.setCategoryDesc(object.getString("categoryDesc"));
                                    secondcategories.setCategoryImg(object.getString("categoryImg"));
                                    secondcategories.setSecondCategoryType(object.getString("secondCategoryType"));
                                    secondcategories.setExtendCondition(object.getString("extendCondition"));
                                    data.add(secondcategories);
                                    adapterTwo.notifyDataSetChanged();
                                    gvPinpai.setOnItemClickListener(new GridViewOneItemOnClick());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(TypeOneActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
    }

    public void doEsc(View v) {
        finish();
    }
}
