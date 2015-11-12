package com.myshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myshopapp.ProductListActivity;
import com.myshopapp.R;
import com.myshopapp.SubjectActivity;
import com.myshopapp.entity.HomeDetails;
import com.myshopapp.entity.HomeView;
import com.myshopapp.utils.ImageLoaderUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/14.
 */
public class HomeViewAdapter extends BaseAdapter{
    private Context context;
    private List<HomeView> list;
    private boolean first=true;
    private int mPosition;
    public HomeViewAdapter(Context context, List<HomeView> list1) {
        this.context = context;
        List<HomeView> l=new ArrayList<>();
        for (HomeView h:list1){
            if (h.getHomeStyle()!=null&&h.getHomeStyle().equals("3")){
                l.add(h);
            }
        }
        list1.removeAll(l);
        this.list = list1;

    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    private class ViewHolder{
        public TextView title;
        public LinearLayout container;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        mPosition=position;
        HomeView h=list.get(position);
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.homeview,null);
            LinearLayout container=(LinearLayout)convertView.findViewById(R.id.container);
            holder.container=container;
            holder.title=(TextView)convertView.findViewById(R.id.title);
            convertView.setTag(holder);

        }else{
            holder=(ViewHolder)convertView.getTag();
        }

            if (h.getHomeStyle()!=null){
                switch(h.getHomeStyle()){

                    case "1":
                        holder.title.setText(h.getHomeTitle());
                        List<HomeDetails> l1=h.getHomeDetailses();
                        holder.container.removeAllViews();
                        View v1=LayoutInflater.from(context).inflate(R.layout.homeview_item2,null);
                        ImageView pic1=(ImageView)v1.findViewById(R.id.pic1);
                        pic1.setOnClickListener(new AdapterListener());
                        ImageView pic2=(ImageView)v1.findViewById(R.id.pic2);
                        pic2.setOnClickListener(new AdapterListener());
                        ImageView pic3=(ImageView)v1.findViewById(R.id.pic3);
                        pic3.setOnClickListener(new AdapterListener());
                        ImageView pic4=(ImageView)v1.findViewById(R.id.pic4);
                        pic4.setOnClickListener(new AdapterListener());
                        TextView title1=(TextView)v1.findViewById(R.id.title1);
                        TextView title2=(TextView)v1.findViewById(R.id.title2);
                        TextView title3=(TextView)v1.findViewById(R.id.title3);
                        TextView title4=(TextView)v1.findViewById(R.id.title4);
                        TextView rate1=(TextView)v1.findViewById(R.id.rate1);
                        TextView rate2=(TextView)v1.findViewById(R.id.rate2);
                        TextView rate3=(TextView)v1.findViewById(R.id.rate3);
                        TextView rate4=(TextView)v1.findViewById(R.id.rate4);
                        for (int i=0;i<l1.size();i++){
                            HomeDetails h1=l1.get(i);
                            switch(i){
                                case 0:
                                    ImageLoaderUtil.display(h1.getImage(),pic1);
                                    title1.setText(h1.getTitle());
                                    rate1.setText(h1.getSubtitle());
                                    break;
                                case 1:
                                    ImageLoaderUtil.display(h1.getImage(),pic2);
                                    title2.setText(h1.getTitle());
                                    rate2.setText(h1.getSubtitle());
                                    break;
                                case 2:
                                    ImageLoaderUtil.display(h1.getImage(),pic3);
                                    title3.setText(h1.getTitle());
                                    rate3.setText(h1.getSubtitle());
                                    break;
                                case 3:
                                    ImageLoaderUtil.display(h1.getImage(),pic4);
                                    title4.setText(h1.getTitle());
                                    rate4.setText(h1.getSubtitle());
                                    break;
                            }
                        }
                        holder.container.addView(v1);
                        break;

                    case "2":

                        holder.title.setText(h.getHomeTitle());
                        List<HomeDetails> l=h.getHomeDetailses();
                        holder.container.removeAllViews();
                        for (final HomeDetails d:l){
                            ImageView imageView=(ImageView)LayoutInflater.from(context).inflate(R.layout.homeview_item1,null);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=null;
                                    switch(d.getType()){
                                        case 100:
                                            intent=new Intent(context, SubjectActivity.class);
                                            break;
                                        case 5:
                                            intent=new Intent(context, ProductListActivity.class);
                                            break;
                                    }
                                    intent.putExtra("type_argu",d.getTypeArgu());
                                    intent.putExtra("link_title",d.getTitle());
                                    context.startActivity(intent);
                                }
                            });
                            ImageLoaderUtil.display(d.getImage(),imageView);
                            holder.container.addView(imageView);
                        }
                        break;
                    case "3":
                        holder.title.setText(h.getHomeTitle());
                        break;
                }
            }


        return convertView;
    }
    private class AdapterListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,ProductListActivity.class);
            switch(v.getId()){

                case R.id.pic1:

                    intent.putExtra("link_title",list.get(mPosition).getHomeDetailses().get(0).getTitle());
                    intent.putExtra("type_argu",list.get(mPosition).getHomeDetailses().get(0).getTypeArgu());
                    context.startActivity(intent);
                    break;
                case R.id.pic2:

                    intent.putExtra("link_title",list.get(mPosition).getHomeDetailses().get(1).getTitle());
                    intent.putExtra("type_argu",list.get(mPosition).getHomeDetailses().get(1).getTypeArgu());
                    context.startActivity(intent);
                    break;
                case R.id.pic3:

                    intent.putExtra("link_title",list.get(mPosition).getHomeDetailses().get(2).getTitle());
                    intent.putExtra("type_argu",list.get(mPosition).getHomeDetailses().get(2).getTypeArgu());
                    context.startActivity(intent);
                    break;
                case R.id.pic4:

                    intent.putExtra("link_title",list.get(mPosition).getHomeDetailses().get(3).getTitle());
                    intent.putExtra("type_argu",list.get(mPosition).getHomeDetailses().get(3).getTypeArgu());
                    context.startActivity(intent);
                    break;
            }
        }
    }

}
