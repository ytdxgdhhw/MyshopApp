package com.myshopapp.hhw.entity;

import java.io.Serializable;

/**
 * Created by wei on 2015/10/16.
 */
public class Productlist_Pictext implements Serializable{
    private String name;
    private String activity_desc;
    private String activeid;
    private String type;
    private String activeStatus;
    private String havegift;
    private String id;
    private String pic;
    private Price price1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity_desc() {
        return activity_desc;
    }

    public void setActivity_desc(String activity_desc) {
        this.activity_desc = activity_desc;
    }

    public String getActiveid() {
        return activeid;
    }

    public void setActiveid(String activeid) {
        this.activeid = activeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getHavegift() {
        return havegift;
    }

    public void setHavegift(String havegift) {
        this.havegift = havegift;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Price getPrice1() {
        return price1;
    }

    public void setPrice1(Price price1) {
        this.price1 = price1;
    }

    public Price getPrice2() {
        return price2;
    }

    public void setPrice2(Price price2) {
        this.price2 = price2;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private Price price2;
    private String rebate;
    private String number;

}
