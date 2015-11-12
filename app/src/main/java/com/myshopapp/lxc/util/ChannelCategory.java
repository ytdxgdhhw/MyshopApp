package com.myshopapp.lxc.util;

/**
 * Created by Administrator on 2015/10/16.
 */
public class ChannelCategory {
    private String ChannelTitle;
    private  int  ChannelId;

    public String getChannelTitle() {
        return ChannelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        ChannelTitle = channelTitle;
    }

    public int getChannelId() {
        return ChannelId;
    }

    public void setChannelId(int channelId) {
        ChannelId = channelId;
    }

    public ChannelCategory(String channelTitle, int channelId) {
        ChannelTitle = channelTitle;
        ChannelId = channelId;
    }
}
