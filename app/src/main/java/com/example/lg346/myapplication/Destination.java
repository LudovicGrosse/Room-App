package com.example.lg346.myapplication;

import android.util.Log;

import java.sql.SQLOutput;
import java.util.SortedMap;

public class Destination {
    private String type, title, media;
    private double myLatitude, myLongitude;
    private double distance;

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

    public double getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Destination(String type, String title, String media, double myLatitude, double myLongitude) {
        this.type = type;
        this.title = title;
        this.media = media;
        this.distance = 0;
        this.myLatitude = myLatitude;
        this.myLongitude = myLongitude;
    }

    public double getMyLatitude() {
        return myLatitude;
    }

    public void setMyLatitude(double myLatitude) {
        this.myLatitude = myLatitude;
    }

    public double getMyLongitude() {
        return myLongitude;
    }

    public void setMyLongitude(double myLongitude) {
        this.myLongitude = myLongitude;
    }

    public double calculDistance(double latitude, double longitude){
        //double latitude2 = latitude*Math.PI / 180;
       // double longitude2 = longitude*Math.PI / 180;
        //double myLongitude2 = myLatitude * Math.PI / 180;
        //double myLatitude2 = myLongitude* Math.PI / 180;
        double latitude2 = 0.5 ;
        double myLatitude2 = 2 ;
        double longitude2 = 1 ;
        double myLongitude2 = 3;
        double R = 6371 ;
        distance = R * Math.acos(Math.cos(latitude2) * Math.cos(myLatitude2) *
                Math.cos(longitude2 - myLongitude2) + Math.sin(latitude2) *
                Math.sin(myLatitude2));
        Log.d("TEST",Double.toString(distance));
        return distance ;
    }
}

