package com.myshopapp.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
@DatabaseTable
public class HomeView {
    @DatabaseField
    private String homeTitle;
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String homeStyle;
    private String
            startTime,endTime;

    private List<HomeDetails> homeDetailses=new ArrayList<>();

    //@ForeignCollectionField
    private ForeignCollection<HomeDetails> foreignCollection;


//    @ForeignCollectionField
//    private List<HomeDetails> homeDetailses= new ArrayList<HomeDetails>();

    public List<HomeDetails> getHomeDetailses() {
        return homeDetailses;
    }

    public void setHomeDetailses(List<HomeDetails> homeDetailses) {
        this.homeDetailses = homeDetailses;
    }

    public ForeignCollection<HomeDetails> getForeignCollection() {
        return foreignCollection;
    }

    public void setForeignCollection(ForeignCollection<HomeDetails> foreignCollection) {
        this.foreignCollection = foreignCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomeTitle() {
        return homeTitle;
    }

    public void setHomeTitle(String homeTitle) {
        this.homeTitle = homeTitle;
    }

    public String getHomeStyle() {
        return homeStyle;
    }

    public void setHomeStyle(String homeStyle) {
        this.homeStyle = homeStyle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


}
