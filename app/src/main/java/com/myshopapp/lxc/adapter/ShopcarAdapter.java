package com.myshopapp.lxc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myshopapp.R;
import com.myshopapp.lxc.dao.Product;
import com.myshopapp.utils.ImageLoaderUtil;

import java.util.List;


/**
 * Created by Administrator on 2015/10/14.
 */
public class ShopcarAdapter extends BaseAdapter{


        private List<Product> items;
        private Context context;
        public ShopcarAdapter(List<Product> items, Context context) {
                this.items = items;
                this.context = context;
        }

        @Override
        public int getCount() {
                return items.size();
        }

        @Override
        public Object getItem(int position) {
                return items.get(position);
        }

        @Override
        public long getItemId(int position) {
                return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder=null;
                if(convertView==null){
                     convertView= LayoutInflater.from(context).inflate(R.layout.product_layout,null);
                        holder=new ViewHolder();
                        holder.title=(TextView)convertView.findViewById(R.id.title);
                        holder.color=(TextView)convertView.findViewById(R.id.color);
                        holder.price=(TextView)convertView.findViewById(R.id.price);
                        holder.size=(TextView)convertView.findViewById(R.id.size);
                        holder.number=(TextView)convertView.findViewById(R.id.number);
                        holder.provide=(TextView)convertView.findViewById(R.id.provide);
                        holder.icon=(ImageView)convertView.findViewById(R.id.icon);
                        convertView.setTag(holder);

                }else {
                        holder = (ViewHolder) convertView.getTag();
                }
                        holder.title.setText(items.get(position).getProductName());
                        holder.color.setText(items.get(position).getProductColor());
                        holder.price.setText(items.get(position).getProductSize());
                        holder.size.setText(items.get(position).getProductNumber());
                        holder.number.setText(items.get(position).getProductNumber());
                        holder.provide.setText(items.get(position).getProductProvide());
                        ImageLoaderUtil.display(items.get(position).getProductIcon(), holder.icon);
                return convertView;
        }

        public static class ViewHolder{
                private TextView title;
                private TextView color;
                private TextView price;
                private TextView size;
                private TextView number;
                private TextView provide;
                private ImageView icon;
        }
}
