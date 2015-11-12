package com.myshopapp.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2015/10/13.
 */
@DatabaseTable
public class HomeBanner {
    private String type;
    @DatabaseField(generatedId = true)
    private int idi;
    private String id;
    private String title;
    @DatabaseField
    private String pic;
    private String type_argu;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_argu() {
        return type_argu;
    }

    public void setType_argu(String type_argu) {
        this.type_argu = type_argu;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
