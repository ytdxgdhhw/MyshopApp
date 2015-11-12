package com.myshopapp.hhw.entity;

/**
 * Created by wei on 2015/10/13.
 */
public class Hot_Categories {
    private String categoryId;
    private String categoryLevel;
    private String categoryName;
    private String categoryDesc;
    private String categoryImg;
    private String secondCategoryType;
    private String extendCondition;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(String categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public String getSecondCategoryType() {
        return secondCategoryType;
    }

    public void setSecondCategoryType(String secondCategoryType) {
        this.secondCategoryType = secondCategoryType;
    }

    public String getExtendCondition() {
        return extendCondition;
    }

    public void setExtendCondition(String extendCondition) {
        this.extendCondition = extendCondition;
    }

}
