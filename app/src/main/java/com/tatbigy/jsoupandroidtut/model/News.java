package com.tatbigy.jsoupandroidtut.model;

/**
 * Created by Ahmed on 12/24/15.
 * 03
 * JsoupAndroidTut
 */
public class News {
    String title;
    String desc;
    String image;
    String date;
    String auther;
    String np_views;

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNp_views() {
        return np_views;
    }

    public void setNp_views(String np_views) {
        this.np_views = np_views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
