package com.myshopapp.hhw;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myshopapp.R;
import com.myshopapp.application.YGApplication;
import com.myshopapp.hhw.adapter.WutiAdapter;
import com.myshopapp.hhw.entity.Product;
import com.myshopapp.hhw.entity.Productlist_Pictext;
import com.myshopapp.hhw.entity.Simpleshow;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<String> mData;
    private WutiAdapter adapter;
    private RequestQueue requestQueue;
    private String url;
    private TextView tvTitle,RightPrice,OldPrice;
    private TextView spms;
    private String info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mData=new ArrayList<String>();
        adapter=new WutiAdapter(this,mData);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        spms=(TextView)findViewById(R.id.spms);
        url=getIntent().getStringExtra("productid");
        //Productlist_Pictext p =(Productlist_Pictext)getIntent().getSerializableExtra("product");
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        RightPrice=(TextView)findViewById(R.id.RightPrice);
        OldPrice=(TextView)findViewById(R.id.OldPrice);
        //tvTitle.setText(p.getName());
       // OldPrice.setText("¥" + p.getPrice1().getValue());
       // RightPrice.setText("¥" + p.getPrice2().getValue());
        loadDataI();
        loadDataII();

    }
    private void loadDataII() {
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/product?productid="+url+"&addPriceProductid=&addPriceSizeNo=&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            JSONObject json=jsonObject.getJSONObject("product");
                            JSONObject jso=json.getJSONObject("detail");
                            info=jso.getString("product_multidisc");
                            info=info.replace("|","\n");
                            spms.setText(info);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(DetailActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
    }

    private void loadDataI() {
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/product?productid="+url+"&addPriceProductid=&addPriceSizeNo=&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            JSONObject json=jsonObject.getJSONObject("product");

                            JSONObject jso=json.getJSONObject("simpleshow");
                            JSONArray jsonArray=jso.getJSONArray("bigimage");
                            int length=jsonArray.length();
                            List<String> image=new ArrayList<>();
                            Simpleshow simpleshow=new Simpleshow();
                            Product product=new Product();
                            for(int i=0;i<length;i++){
                                String img=jsonArray.getString(i);

                              mData.add(img);
                            }
                            simpleshow.setBigimage(image);
                            product.setSimpleshow(simpleshow);
                            adapter.notifyDataSetChanged();
                            String title=json.getJSONObject("simpleshow").getJSONObject("product_property").getString("title");
                            String price1=json.getJSONObject("simpleshow").getJSONObject("product_property").getJSONObject("price2").getString("value");
                            String price2=json.getJSONObject("simpleshow").getJSONObject("product_property").getJSONObject("price1").getString("value");//市场价
                            tvTitle.setText(title);
                            OldPrice.setText("¥" + price2);
                            RightPrice.setText("¥" + price1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(DetailActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
        adapter.notifyDataSetChanged();
    }
    public void doEsc(View v) {
        finish();
    }
}
