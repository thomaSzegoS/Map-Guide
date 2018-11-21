package com.example.mapguide.mapguide.Model;

public class ImageModel {

    private String linkImg, id, desc, title;
    private Place place;

    public ImageModel(String link, String id, String desc, String title) {
        this.linkImg = link;
        this.id = id;
        this.desc = desc;
        this.title = title;
        this.place = new Place ();
    }

    public ImageModel() {
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String link) {
        this.linkImg = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
