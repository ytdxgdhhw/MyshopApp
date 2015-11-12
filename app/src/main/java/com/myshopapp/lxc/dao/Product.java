package com.myshopapp.lxc.dao;

/**
 * Created by Administrator on 2015/10/14.
 */
public class Product {
    private String ProductName;
    private String ProductColor;
    private long ProductPrice;
    private String ProductSize;
    private int ProductNumber;
    private String ProductProvide;
    private String  ProductIcon;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductColor() {
        return ProductColor;
    }

    public void setProductColor(String productColor) {
        ProductColor = productColor;
    }

    public long getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(long productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductSize() {
        return ProductSize;
    }

    public void setProductSize(String productSize) {
        ProductSize = productSize;
    }

    public int getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(int productNumber) {
        ProductNumber = productNumber;
    }

    public String getProductProvide() {
        return ProductProvide;
    }

    public void setProductProvide(String productProvide) {
        ProductProvide = productProvide;
    }

    public String getProductIcon() {
        return ProductIcon;
    }

    public void setProductIcon(String productIcon) {
        ProductIcon = productIcon;
    }
}
