package com.myshopapp.hhw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.myshopapp.R;
import com.myshopapp.hhw.entity.Secondcategories;
import com.myshopapp.utils.ImageLoaderUtil;


import java.util.List;

/**
 * Created by wei on 2015/10/13.
 */
public class TypeTwoAdapter extends BaseAdapter {
    private Context context;
    private List<Secondcategories> list;


    public TypeTwoAdapter(Context context, List<Secondcategories> list) {
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
        private TextView ItemTextView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Secondcategories sortItem=list.get(position);
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.type_two_layout,null);
            viewHolder.ItemTextView=(TextView)convertView.findViewById(R.id.ItemTextView);
            viewHolder.ItemImageView=(ImageView)convertView.findViewById(R.id.ItemImageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.ItemTextView.setText(sortItem.getCategoryName());

        ImageLoaderUtil.display(sortItem.getCategoryImg(),viewHolder.ItemImageView);
        return convertView;
    }
}
