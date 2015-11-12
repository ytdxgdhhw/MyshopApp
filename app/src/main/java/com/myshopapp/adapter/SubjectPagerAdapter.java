package com.myshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myshopapp.ProductListActivity;
import com.myshopapp.R;
import com.myshopapp.entity.SubjectDetails;
import com.myshopapp.hhw.DetailActivity;
import com.myshopapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class SubjectPagerAdapter extends PagerAdapter{
    private List<SubjectDetails> list;
    private Context context;

    public SubjectPagerAdapter(List<SubjectDetails> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView= (ImageView)LayoutInflater.from(context).inflate(R.layout.picture,null);
        ImageLoaderUtil.display(list.get(position).getImage(),imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=null;
                switch (list.get(position).getType()){
                    case 5:
                        intent=new Intent(context, ProductListActivity.class);
                        intent.putExtra("type_argu",list.get(position).getTypeArgu());
                        break;
                    case 9:

                        intent=new Intent(context, DetailActivity.class);
                        intent.putExtra("productid",list.get(position).getTypeArgu());
                        //intent.putExtra("product",mData.get(position));
                        break;
                }

                context.startActivity(intent);
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
