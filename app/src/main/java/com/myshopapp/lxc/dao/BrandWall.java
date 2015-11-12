package com.myshopapp.lxc.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/20.
 */
public class BrandWall implements Parcelable{
    private String name;
    private String brandEnglishName;
    private String id;
    private String pic ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandEnglishName() {
        return brandEnglishName;
    }

    public void setBrandEnglishName(String brandEnglishName) {
        this.brandEnglishName = brandEnglishName;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(brandEnglishName);
        dest.writeString(id);
        dest.writeString(pic);
    }
    public static final Parcelable.Creator<BrandWall> CREATOR = new Parcelable.Creator<BrandWall>()
    {
        public BrandWall createFromParcel(Parcel in)
        {
            return new BrandWall(in);
        }

        public BrandWall[] newArray(int size)
        {
            return new BrandWall[size];
        }
    };

    public BrandWall() {
    }

    public  BrandWall(Parcel in){
        name =in.readString();
        brandEnglishName=in.readString();
        id=in.readString();
        pic=in.readString();
    }

}
