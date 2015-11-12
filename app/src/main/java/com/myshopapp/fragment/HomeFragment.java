package com.myshopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.j256.ormlite.dao.Dao;
import com.myshopapp.R;
import com.myshopapp.adapter.HomeViewAdapter;
import com.myshopapp.adapter.TopViewAdapter;
import com.myshopapp.application.YGApplication;
import com.myshopapp.db.DatabaseOpenHelper;
import com.myshopapp.entity.HomeBanner;
import com.myshopapp.entity.HomeDetails;
import com.myshopapp.entity.HomeView;
import com.myshopapp.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class HomeFragment extends Fragment {
    private View view;
    private ImageView doSearch;
    private ViewPager viewPager;
    private List<HomeBanner> topList;
    private TopViewAdapter adapter;
    private List<HomeView> list;
    private ListView listView;
    private HomeViewAdapter hvAdapter;
    private RadioGroup radioGroup;
    private Dao<HomeBanner,Integer> hbDao;
    private Dao<HomeView,Integer> hvDao;
    private Dao<HomeDetails,Integer> hdDao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,null);
        init();
        return view;
    }
    private void init(){
        doSearch=(ImageView)view.findViewById(R.id.doSearch);
        doSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);


            }
        });
        hbDao= DatabaseOpenHelper.getInstance(getActivity()).getHbDao();
        hvDao=DatabaseOpenHelper.getInstance(getActivity()).getHmViewDao();
        hdDao= DatabaseOpenHelper.getInstance(getActivity()).getHdDao();
        list=new ArrayList<>();
        listView=(ListView)view.findViewById(R.id.listView);
        View headerView=LayoutInflater.from(getActivity()).inflate(R.layout.headerview,null);
        viewPager=(ViewPager)headerView.findViewById(R.id.topPics);
        viewPager.setOffscreenPageLimit(6);
        radioGroup=(RadioGroup)headerView.findViewById(R.id.radios);
        topList=new ArrayList<>();
        try {
            if (hbDao.queryForAll()!=null){
                topList.clear();
                topList.addAll(hbDao.queryForAll());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter=new TopViewAdapter(getActivity(),topList);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new MyListener());
        listView.addHeaderView(headerView);
        try {
            if (hvDao.queryForAll()!=null){
                list.clear();
                list.addAll(hvDao.queryForAll());
                for(HomeView v: list){
                    v.getHomeDetailses().addAll(hdDao.queryForEq("fid",v.getId()));
                }
            }
            List<HomeDetails> tp= hdDao.queryForAll();
            Log.i("d","d");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        hvAdapter=new HomeViewAdapter(getActivity(),list);
        listView.setAdapter(hvAdapter);
        StringRequest request=new StringRequest("http://mobile.yougou.com/v_1.8/home?&",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            hvDao.delete(hvDao.queryForAll());
                            hbDao.delete(hbDao.queryForAll());
                            hdDao.delete(hdDao.queryForAll());
                            topList.clear();
                            list.clear();
                            JSONObject jsonObject=new JSONObject(s);
                            JSONArray jsonArray=jsonObject.getJSONArray("home_banner");
                            int length=jsonArray.length();
                            for(int i=0;i<length;i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                HomeBanner homeBanner=new HomeBanner();
                                homeBanner.setType(object.getString("type"));
                                homeBanner.setTitle(object.getString("title"));
                                homeBanner.setPic(object.getString("pic"));
                                homeBanner.setType_argu(object.getString("type_argu"));
                                topList.add(homeBanner);
                                //存入数据库
                                hbDao.createOrUpdate(homeBanner);

                            }

                            adapter.notifyDataSetChanged();
                            //解析homeview
                            JSONArray homeviewJsonArray=jsonObject.getJSONArray("homeview");
                            for(int i=0;i<homeviewJsonArray.length();i++){
                                JSONObject object=homeviewJsonArray.getJSONObject(i);
                                HomeView homeView=new HomeView();
                                homeView.setEndTime(object.getString("end_time"));
                                homeView.setHomeTitle(object.getString("home_title"));
                                homeView.setHomeStyle((String) object.get("home_style"));

                                int id=hvDao.create(homeView);

                                List<HomeDetails> list1=new ArrayList<>();
                                //解析homedetails
                                JSONArray jsonArray1=object.getJSONArray("home_details");
                                for (int j=0;j<jsonArray1.length();j++){
                                    JSONObject hd=jsonArray1.getJSONObject(j);
                                    HomeDetails homeDetails=new HomeDetails();
                                    homeDetails.setType(hd.getInt("type"));
                                    homeDetails.setImage(hd.getString("image"));
                                    homeDetails.setTitle(hd.getString("title"));
                                    homeDetails.setLinkTitle(hd.getString("link_title"));
                                    homeDetails.setSubtitle(hd.getString("subtitle"));
                                    homeDetails.setTypeArgu(hd.getString("type_argu"));
                                    homeDetails.setDepa(homeView);
                                    //
                                    homeDetails.setFid(homeView.getId());
                                    list1.add(homeDetails);
                                    hdDao.createOrUpdate(homeDetails);
                                }

                                homeView.setHomeDetailses(list1);
                                list.add(homeView);



                            }
                            hvAdapter.notifyDataSetChanged();
                            initButton();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                initButton();
                Toast.makeText(getActivity().getApplication(),"网络连接超时",Toast.LENGTH_SHORT).show();
            }
        });
        YGApplication.getInstance().getRequestQueue().add(request);

    }
    private void initButton(){
        for (int i=0;i<topList.size();i++){
            RadioButton radioButton=(RadioButton)LayoutInflater.from(getActivity()).inflate(R.layout.radiobutton,null);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
        radioGroup.check(0);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //DatabaseOpenHelper.getInstance(getActivity()).close();
    }

    private class MyListener implements ViewPager.OnPageChangeListener{
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

}
