package com.example.lg346.myapplication;

// -------- Classe pour le traitement des données JSON ----------

public class DestinationClass {

    private String room;
    private Integer image;

    public DestinationClass(String room, String etat) {
        this.room = room;
        this.image = (etat.equals("1")) ? R.drawable.green_dot : R.drawable.red_dot ;
    }

    public String getRoom() {
        return room;
    }

    public Integer getImage(){
        return image;
    }

}