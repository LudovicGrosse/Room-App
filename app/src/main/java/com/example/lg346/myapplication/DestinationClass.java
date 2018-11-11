package com.example.lg346.myapplication;

import android.util.Log;

import java.util.Comparator;

public class DestinationClass {
    private String type, title, media; // type, display, media = type, titre, image

    public static double latitudeTel, longitudeTel;
    private double latitudeDest, longitudeDest;
    private double distance;

    public static double getLatitudeTel() {
        return latitudeTel;
    }

    public static void setLatitudeTel(double latitudeTel) {
        DestinationClass.latitudeTel = latitudeTel;
    }

    public static double getLongitudeTel() {
        return longitudeTel;
    }

    public static void setLongitudeTel(double longitudeTel) {
        DestinationClass.longitudeTel = longitudeTel;
    }

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

    public DestinationClass(String type, String title, String media, double latitudeDest, double longitudeDest) {
        this.type = type;
        this.title = title;
        this.media = media;
        this.distance = 0;
        this.latitudeDest = latitudeDest;
        this.longitudeDest = longitudeDest;
        calculDistance();
    }

    public double getLatitudeDest() {
        return latitudeDest;
    }

    public void setLatitudeDest(double latitudeDest) {
        this.latitudeDest = latitudeDest;
    }

    public double getLongitudeDest() {
        return longitudeDest;
    }

    public void setLongitudeDest(double longitudeDest) {
        this.longitudeDest = longitudeDest;
    }

    public void calculDistance(){
        double lat1 = latitudeTel*Math.PI / 180;
        double lon1 = longitudeTel*Math.PI / 180;
        double lon2 = longitudeDest * Math.PI / 180;
        double lat2 = latitudeDest* Math.PI / 180;
        double R = 6371 ;
        distance = R * Math.acos(Math.cos(lat1) * Math.cos(lat2) *
                Math.cos(lon1 - lon2) + Math.sin(lat1) *
                Math.sin(lat2));
        // arrondi a 2 chiffres apres la virgule
        distance = distance * 100;
        int d = (int) distance ;
        distance = (double) d;
        distance = distance / 100 ;
    }
}

class SortDestClass implements Comparator<DestinationClass>
{
    public int compare(DestinationClass a, DestinationClass b)
    {
        return a.getDistance() > b.getDistance() ? 1 : -1;
    }
}

