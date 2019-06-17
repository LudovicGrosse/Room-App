package com.example.lg346.myapplication;

// -------- Classe pour le traitement des données json ----------

import android.media.Image;

public class DestinationClass {

    private String room;
    private String etat;
    private Integer image;

    public DestinationClass(String room, String etat) {
        this.room = room;
        this.etat = etat;
        this.image = (etat.equals("1")) ? R.drawable.green_dot : R.drawable.red_dot ;
    }

    public String getRoom() {
        return room;
    }

    public String getEtat() {
        return etat;
    }

    public Integer getImage(){
        return image;
    }

}