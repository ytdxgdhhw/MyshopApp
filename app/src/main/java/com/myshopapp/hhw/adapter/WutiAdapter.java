package com.myshopapp.hhw.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.myshopapp.R;

import com.myshopapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by wei on 2015/10/19.
 */
public class WutiAdapter extends PagerAdapter {
    private Context context;
    private List<String> list;

    public WutiAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view=null;



            view=(ImageView) LayoutInflater.from(context).inflate(R.layout.wuti_priture,null);

        ImageLoaderUtil.display(list.get(position),view);
            container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
