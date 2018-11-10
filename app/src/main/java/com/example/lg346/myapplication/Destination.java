package com.example.lg346.myapplication;

public class Destination {
    private String type, title, media;
    private float distance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Destination(String type, String title, String media, float distance) {
        this.type = type;
        this.title = title;
        this.media = media;
        this.distance = distance;
    }
}
