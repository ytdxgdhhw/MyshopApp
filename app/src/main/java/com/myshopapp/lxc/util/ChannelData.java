package com.myshopapp.lxc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class ChannelData {
    private List<ChannelCategory> list;

    public ChannelData(){
        super();
        list=new ArrayList<>();
        list.add(new ChannelCategory("推荐", 1));
        list.add(new ChannelCategory("运动户外馆",2));
        list.add(new ChannelCategory("鞋靴馆", 3));
        list.add(new ChannelCategory("女装馆",4));
        list.add(new ChannelCategory("男装馆",5));
        list.add(new ChannelCategory("儿童馆",6));
        list.add(new ChannelCategory("家品管",7));
        list.add(new ChannelCategory("内衣馆",8));
        list.add(new ChannelCategory("配饰",9));
        list.add(new ChannelCategory("箱包馆",0));



    }
    public  List<ChannelCategory> getChannel(){
        return list;
    }


}
