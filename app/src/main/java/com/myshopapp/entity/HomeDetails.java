package com.myshopapp.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2015/10/13.
 */
@DatabaseTable
public class HomeDetails {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String image;
    @DatabaseField
    private String  linkTitle;
    private int type,enable;
    private String typeArgu,
       title,subtitle;

    //@DatabaseField(foreign=true,foreignAutoRefresh=true,columnName = "fid")
    private HomeView depa;

    @DatabaseField
    private int fid;

    public HomeView getDepa() {
        return depa;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setDepa(HomeView depa) {
        this.depa = depa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getTypeArgu() {
        return typeArgu;
    }

    public void setTypeArgu(String typeArgu) {
        this.typeArgu = typeArgu;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
