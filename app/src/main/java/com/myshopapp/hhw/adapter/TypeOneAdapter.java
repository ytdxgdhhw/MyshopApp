package com.myshopapp.hhw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.myshopapp.R;
import com.myshopapp.hhw.application.CCApplication;
import com.myshopapp.hhw.entity.Brands;
import com.myshopapp.utils.ImageLoaderUtil;

import java.util.List;


/**
 * Created by wei on 2015/10/13.
 */
public class TypeOneAdapter extends BaseAdapter {
    private Context context;
    private List<Brands> list;


    public TypeOneAdapter(Context context, List<Brands> list) {
        this.context = context;
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        private ImageView ItemImageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Brands sortItem=list.get(position);
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.type_one_layout,null);

            viewHolder.ItemImageView=(ImageView)convertView.findViewById(R.id.ItemImageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }


        ImageLoaderUtil.display(sortItem.getBrandImg(),viewHolder.ItemImageView);
        return convertView;
    }
}
