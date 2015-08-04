package com.htech.restaurant.vos;

/**
 * Created by software on 8/3/15.
 */
public class SubMenu {

    String subCatName;
    String subIcon;
    int id;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getSubIcon() {
        return subIcon;
    }

    public void setSubIcon(String subIcon) {
        this.subIcon = subIcon;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
