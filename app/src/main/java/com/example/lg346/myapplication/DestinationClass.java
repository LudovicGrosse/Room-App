package com.example.lg346.myapplication;

// -------- Classe pour le traitement des données JSON ----------

public class DestinationClass {

    private String room;
    private Integer image;

    public DestinationClass(String room, long period) {
        this.room = room;
        if (period > 300) image = R.drawable.green_dot;  // libre
        else if (period < 0) image = R.drawable.logo;    // indéfini
        else image = R.drawable.red_dot;                 // occupé
    }

    public String getRoom() {
        return room;
    }

    public Integer getImage(){
        return image;
    }

}