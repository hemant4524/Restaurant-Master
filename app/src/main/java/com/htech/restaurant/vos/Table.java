package com.htech.restaurant.vos;

/**
 * Created by software on 7/29/15.
 */
public class Table {
    private int id;
    private String name;

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    private String thumb;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }
}
