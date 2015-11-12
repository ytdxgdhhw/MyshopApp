package com.myshopapp.lxc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshopapp.R;
import com.myshopapp.hhw.entity.Secondcategories;
import com.myshopapp.lxc.dao.BrandWall;
import com.myshopapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/10/20.
 */
public class BrandAdapter extends BaseAdapter{
    private List<BrandWall> list;
    private Context context;

    public BrandAdapter(List<BrandWall> list, Context context) {
        this.list = list;
        this.context = context;
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
        BrandWall sortItem=list.get(position);
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.brand_item,null);
            viewHolder.ItemTextView=(TextView)convertView.findViewById(R.id.ItemTextView);
            viewHolder.ItemImageView=(ImageView)convertView.findViewById(R.id.ItemImageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.ItemTextView.setText(sortItem.getName());

        ImageLoaderUtil.display(sortItem.getPic(), viewHolder.ItemImageView);
        return convertView;

    }
}
