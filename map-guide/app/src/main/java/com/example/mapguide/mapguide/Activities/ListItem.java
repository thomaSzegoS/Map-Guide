package com.example.mapguide.mapguide.Activities;

public class ListItem {

    private String head;
    private String desc;
    private String imageUrl;
    private String Lat;
    private String Lon;

    public ListItem(String head,String desc, String imageUrl, String Lat, String Lon) {
        this.head = head;
        this.desc = desc;
        this.Lat = Lat;
        this.Lon = Lon;
        this.imageUrl = imageUrl;

    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getLat() {
        return Lat;
    }

    public String getLon() {
        return Lon;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
