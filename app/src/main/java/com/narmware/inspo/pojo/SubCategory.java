package com.narmware.inspo.pojo;

/**
 * Created by rohitsavant on 20/06/18.
 */

public class SubCategory {
    String name,place,time,image;

    public SubCategory(String name, String place, String time,String image) {
        this.name = name;
        this.place = place;
        this.time = time;
        this.image=image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}