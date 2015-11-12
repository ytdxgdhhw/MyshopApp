package com.myshopapp.entity;

/**
 * Created by Administrator on 2015/10/16.
 */
public class SubjectDetails {
    private int type,enable;
    private String typeArgu,linkTitle,image,subjectDesc;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }
}
