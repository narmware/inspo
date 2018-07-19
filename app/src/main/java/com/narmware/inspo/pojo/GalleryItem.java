package com.narmware.inspo.pojo;

/**
 * Created by rohitsavant on 16/07/18.
 */

public class GalleryItem {
    String image,title;

    public GalleryItem(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
