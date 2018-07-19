package com.narmware.inspo.pojo;

import java.util.List;

/**
 * Created by rohitsavant on 03/07/18.
 */

public class CardItem {
    String id,name,occupation,location,image;
    List<String> intrestList;
    List<String> offersList;
    List<String> looksList;

    public CardItem(String id,String name, String occupation, String location, String image, List<String> intrestList, List<String> offersList, List<String> looksList) {
        this.name = name;
        this.occupation = occupation;
        this.location = location;
        this.image = image;
        this.intrestList = intrestList;
        this.offersList = offersList;
        this.looksList = looksList;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIntrestList() {
        return intrestList;
    }

    public void setIntrestList(List<String> intrestList) {
        this.intrestList = intrestList;
    }

    public List<String> getOffersList() {
        return offersList;
    }

    public void setOffersList(List<String> offersList) {
        this.offersList = offersList;
    }

    public List<String> getLooksList() {
        return looksList;
    }

    public void setLooksList(List<String> looksList) {
        this.looksList = looksList;
    }
}
