package com.myshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myshopapp.ProductListActivity;
import com.myshopapp.R;
import com.myshopapp.entity.HomeBanner;
import com.myshopapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class TopViewAdapter extends PagerAdapter{
    private List<HomeBanner> list;
    private Context context;

    public TopViewAdapter(Context context, List<HomeBanner> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        ImageView view=(ImageView) LayoutInflater.from(context).inflate(R.layout.picture,null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductListActivity.class);
                intent.putExtra("link_title",list.get(position).getTitle());
                intent.putExtra("type_argu",list.get(position).getType_argu());
                context.startActivity(intent);
            }
        });
        ImageLoaderUtil.display(list.get(position).getPic(), view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
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
