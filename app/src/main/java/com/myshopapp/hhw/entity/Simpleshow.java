package com.myshopapp.hhw.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2015/10/19.
 */
public class Simpleshow {
    private List<String> bigimage=new ArrayList<>();

    public List<String> getBigimage() {
        return bigimage;
    }

    public void setBigimage(List<String> bigimage) {
        this.bigimage.addAll(bigimage);
    }
}
