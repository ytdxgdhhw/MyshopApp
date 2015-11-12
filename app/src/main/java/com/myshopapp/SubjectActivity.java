package com.myshopapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myshopapp.adapter.SubjectPagerAdapter;
import com.myshopapp.application.YGApplication;
import com.myshopapp.entity.SubjectDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SubjectActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<SubjectDetails> list;
    private SubjectPagerAdapter adapter;
    private String urlDetail;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        urlDetail=getIntent().getStringExtra("type_argu");

        initView();
        loadData();
    }
    private void initView(){
        list=new ArrayList<>();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        radioGroup=(RadioGroup)findViewById(R.id.radios);
        adapter=new SubjectPagerAdapter(list,this);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }
    private void loadData(){

        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/subject?subjectId="+
                urlDetail+"&&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try{
                            JSONObject jsonObject=new JSONObject(s);
                            JSONArray jsonArray=jsonObject.getJSONObject("subject").getJSONArray("subject_details");
                            for (int i=0;i<jsonArray.length();i++){
                                SubjectDetails subjectDetails=new SubjectDetails();
                                subjectDetails.setType(jsonArray.getJSONObject(i).getInt("type"));
                                subjectDetails.setTypeArgu(jsonArray.getJSONObject(i).getString("type_argu"));
                                subjectDetails.setImage(jsonArray.getJSONObject(i).getString("image"));
                                list.add(subjectDetails);
                            }
                            adapter.notifyDataSetChanged();
                            initButton();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplication(),"网络连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
        YGApplication.getInstance().getRequestQueue().add(request);
    }
    private void initButton(){
        for (int i=0;i<list.size();i++){
            RadioButton radioButton=(RadioButton) LayoutInflater.from(this).inflate(R.layout.radiobutton_big,null);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
        radioGroup.check(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        radioGroup.check(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
