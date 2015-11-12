package com.myshopapp.lxc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.myshopapp.R;
import com.myshopapp.fragment.HomeFragment;
import com.myshopapp.lxc.fragment.MyFragmentFive;
import com.myshopapp.lxc.fragment.MyFragmentFour;
import com.myshopapp.lxc.fragment.MyFragmentOne;
import com.myshopapp.lxc.fragment.MyFragmentThree;
import com.myshopapp.lxc.fragment.MyFragmentTwo;
import com.myshopapp.lxc.util.FragmentTabHost;


public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{
    private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView();

    }
    private View v1,v2,v3,v4,v5;
    private void initView() {
        v1= LayoutInflater.from(this).inflate(R.layout.tab_item,null);
        v2= LayoutInflater.from(this).inflate(R.layout.tab_item,null);
        v3= LayoutInflater.from(this).inflate(R.layout.tab_item,null);
        v4= LayoutInflater.from(this).inflate(R.layout.tab_item,null);
        v5= LayoutInflater.from(this).inflate(R.layout.tab_item,null);

        tabHost=(FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.realContact);

        addTabs(v1, 1, "main", R.mipmap.toolbar_home_selected, "首页", HomeFragment.class);
        addTabs(v2, 2, "post", R.mipmap.toolbar_brand_normal, "品牌", MyFragmentTwo.class);
        addTabs(v3, 3, "type", R.mipmap.toolbar_categories_normal, "分类", MyFragmentThree.class);
        addTabs(v4, 4, "shopcar",R.mipmap.toolbar_shopcar_normal,"购物车", MyFragmentFour.class);
        addTabs(v5, 5, "more",R.mipmap.toolbar_more_normal,"更多", MyFragmentFive.class);


    }
    private void addTabs(View view,int id,String tag,int picRes,String tabName,Class clzss) {
    FragmentTabHost.TabSpec tab=tabHost.newTabSpec(tag);
        view.setId(id);
        TextView label=(TextView)view.findViewById(R.id.label);
        ImageView icon=(ImageView)view.findViewById(R.id.icon);
        if(id==1){
            label.setTextColor(getResources().getColor(R.color.color_selected));
        }
        label.setText(tabName);
        icon.setImageResource(picRes);
        tab.setIndicator(view);

        tabHost.addTab(tab, clzss, null);
        tabHost.setOnTabChangedListener(this);


    }


    @Override
    public void onTabChanged(String tabId) {
        switch (tabId){
            case "main":
                changeColor(v1);
                break;
            case "post":
                changeColor(v2);
                break;
            case "type":
                changeColor(v3);
                break;
            case "shopcar":
                changeColor(v4);
                break;
            case "more":
                changeColor(v5);
                break;

        }

    }
    private void changeColor(View view){
        ImageView icon1=(ImageView)v1.findViewById(R.id.icon);
        TextView label1=(TextView)v1.findViewById(R.id.label);


        ImageView icon2=(ImageView)v2.findViewById(R.id.icon);
        TextView label2=(TextView)v2.findViewById(R.id.label);

        ImageView icon3=(ImageView)v3.findViewById(R.id.icon);
        TextView label3=(TextView)v3.findViewById(R.id.label);

        ImageView icon4=(ImageView)v4.findViewById(R.id.icon);
        TextView label4=(TextView)v4.findViewById(R.id.label);

        ImageView icon5=(ImageView)v5.findViewById(R.id.icon);
        TextView label5=(TextView)v5.findViewById(R.id.label);

        switch (view.getId()){
            case 1:
                icon1.setImageResource(R.mipmap.toolbar_home_selected);
                label1.setTextColor(getResources().getColor(R.color.color_selected));

                icon2.setImageResource(R.mipmap.toolbar_brand_normal);
                label2.setTextColor(getResources().getColor(R.color.color_normal));

                icon3.setImageResource(R.mipmap.toolbar_categories_normal);
                label3.setTextColor(getResources().getColor(R.color.color_normal));

                icon4.setImageResource(R.mipmap.toolbar_shopcar_normal);
                label4.setTextColor(getResources().getColor(R.color.color_normal));

                icon5.setImageResource(R.mipmap.toolbar_more_normal);
                label5.setTextColor(getResources().getColor(R.color.color_normal));
                break;
            case 2:
                icon1.setImageResource(R.mipmap.toolbar_home_normal);
                label1.setTextColor(getResources().getColor(R.color.color_normal));

                icon2.setImageResource(R.mipmap.toolbar_brand_selected);
                label2.setTextColor(getResources().getColor(R.color.color_selected));

                icon3.setImageResource(R.mipmap.toolbar_categories_normal);
                label3.setTextColor(getResources().getColor(R.color.color_normal));

                icon4.setImageResource(R.mipmap.toolbar_shopcar_normal);
                label4.setTextColor(getResources().getColor(R.color.color_normal));

                icon5.setImageResource(R.mipmap.toolbar_more_normal);
                label5.setTextColor(getResources().getColor(R.color.color_normal));
                break;
            case 3:
                icon1.setImageResource(R.mipmap.toolbar_home_normal);
                label1.setTextColor(getResources().getColor(R.color.color_normal));

                icon2.setImageResource(R.mipmap.toolbar_brand_normal);
                label2.setTextColor(getResources().getColor(R.color.color_normal));

                icon3.setImageResource(R.mipmap.toolbar_categories_selected);
                label3.setTextColor(getResources().getColor(R.color.color_selected));

                icon4.setImageResource(R.mipmap.toolbar_shopcar_normal);
                label4.setTextColor(getResources().getColor(R.color.color_normal));

                icon5.setImageResource(R.mipmap.toolbar_more_normal);
                label5.setTextColor(getResources().getColor(R.color.color_normal));
                break;
            case 4:
                icon1.setImageResource(R.mipmap.toolbar_home_normal);
                label1.setTextColor(getResources().getColor(R.color.color_normal));

                icon2.setImageResource(R.mipmap.toolbar_brand_normal);
                label2.setTextColor(getResources().getColor(R.color.color_normal));

                icon3.setImageResource(R.mipmap.toolbar_categories_normal);
                label3.setTextColor(getResources().getColor(R.color.color_normal));

                icon4.setImageResource(R.mipmap.toolbar_shopcar_selected);
                label4.setTextColor(getResources().getColor(R.color.color_selected));

                icon5.setImageResource(R.mipmap.toolbar_more_normal);
                label5.setTextColor(getResources().getColor(R.color.color_normal));
                break;
            case 5:
                icon1.setImageResource(R.mipmap.toolbar_home_normal);
                label1.setTextColor(getResources().getColor(R.color.color_normal));

                icon2.setImageResource(R.mipmap.toolbar_brand_normal);
                label2.setTextColor(getResources().getColor(R.color.color_normal));

                icon3.setImageResource(R.mipmap.toolbar_categories_normal);
                label3.setTextColor(getResources().getColor(R.color.color_normal));

                icon4.setImageResource(R.mipmap.toolbar_shopcar_normal);
                label4.setTextColor(getResources().getColor(R.color.color_normal));

                icon5.setImageResource(R.mipmap.toolbar_more_selected);
                label5.setTextColor(getResources().getColor(R.color.color_selected));
                break;




        }
    }
}
