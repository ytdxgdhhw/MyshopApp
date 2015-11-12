package com.myshopapp.hhw.entity;

import java.io.Serializable;

/**
 * Created by wei on 2015/10/16.
 */
public class Price implements Serializable{
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
