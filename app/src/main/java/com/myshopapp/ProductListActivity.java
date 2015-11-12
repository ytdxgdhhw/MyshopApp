package com.myshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myshopapp.application.YGApplication;
import com.myshopapp.hhw.DetailActivity;
import com.myshopapp.hhw.adapter.TypeThreeAdapter;
import com.myshopapp.hhw.application.CCApplication;
import com.myshopapp.hhw.entity.GridViewForScrollView;
import com.myshopapp.hhw.entity.Price;
import com.myshopapp.hhw.entity.Productlist_Pictext;
import com.myshopapp.utils.StringPostRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private GridViewForScrollView gvPinpai;
    private String url;
    private TextView tvTop;
    private List<Productlist_Pictext> mData;
    private TypeThreeAdapter adapterThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_two);
        tvTop=(TextView)findViewById(R.id.tvTop);
        String title=getIntent().getStringExtra("link_title");
        if (title!=null&&title.length()>1){
            tvTop.setText(title);
        }

        gvPinpai = (GridViewForScrollView) findViewById(R.id.gvPinpai);
        url = getIntent().getStringExtra("type_argu");

        mData=new ArrayList<Productlist_Pictext>();
        adapterThree=new TypeThreeAdapter(this,mData);
        gvPinpai.setAdapter(adapterThree);

            loadDataOne();
        gvPinpai.setOnItemClickListener(new GridViewOneItemOnClick());

    }

    public class GridViewOneItemOnClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String pt=mData.get(position).getId();
            Intent intent =null;
            intent =new Intent(ProductListActivity.this,DetailActivity.class);
            intent.putExtra("productid",pt);
            intent.putExtra("product",mData.get(position));
            startActivity(intent);
        }
    }

    private void loadDataOne() {
        StringRequest request = new StringRequest("http://mobile.yougou.com/v_1.8/topics?tagId=&page=1&per_page=18&topicsid="+url+"&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                                JSONArray jsonArray = jsonObject.getJSONArray("productlist_pictext");
                                int length = jsonArray.length();
                                for (int i = 0; i < length; i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    Productlist_Pictext brands = new Productlist_Pictext();
                                    brands.setName(object.getString("name"));
                                    brands.setActivity_desc(object.getString("activity_desc"));
                                    brands.setActiveid(object.getString("activeid"));
                                    brands.setType(object.getString("type"));
                                    //brands.setActiveStatus(object.getString("activeStatus"));
                                    //brands.setHavegift(object.getString("havegift"));
                                    brands.setId(object.getString("id"));
                                    brands.setPic(object.getString("pic"));
                                    //brands.setRebate(object.getString("rebate"));
                                    //brands.setNumber(object.getString("number"));
                                    Price price1=new Price();
                                    price1.setKey(object.getJSONObject("price1").getString("key"));
                                    price1.setValue(object.getJSONObject("price1").getString("value"));
                                    brands.setPrice1(price1);
                                    Price price2=new Price();
                                    price2.setKey(object.getJSONObject("price2").getString("key"));
                                    price2.setValue(object.getJSONObject("price2").getString("value"));
                                    brands.setPrice2(price2);
                                    mData.add(brands);
                                    adapterThree.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ProductListActivity.this, "网络加载失败！", Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);
    }
    public void doEsc(View v) {
        finish();
    }
}
