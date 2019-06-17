package com.example.lg346.myapplication;

import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// -------- Classe pour le transfert des données vers l'afficheur ----------

public class DestinationAdaptater extends BaseAdapter {
    List<DestinationClass> listDest;

    // LayoutInflater aura pour mission de charger notre fichier XML

    LayoutInflater inflater;

    private class ViewHolder {
        TextView Tvroom;
        TextView Tvetat;
        ImageView Ivetat;
    }
    public DestinationAdaptater(Context context, List<DestinationClass> objects) {
        inflater = LayoutInflater.from(context);
        this.listDest = objects;
    }

    // Génère la vue pour un objet -----------------------------

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            Log.v("test", "convertView is null");
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_destination_listview, null);
            holder.Tvroom = convertView.findViewById(R.id.room);
            holder.Tvetat = convertView.findViewById(R.id.etat);
            holder.Ivetat = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
        DestinationClass dest = listDest.get(position);
        holder.Tvroom.setText(dest.getRoom());
        holder.Tvetat.setText(dest.getEtat());
        holder.Ivetat.setImageResource(dest.getImage());

        return convertView;
    }

    // Retourne le nombre d'éléments -----------------------------
    @Override
    public int getCount() {
//  TODO Auto-generated method stub
        return listDest.size();
    }
    // Retourne l'item à la position

    @Override
    public DestinationClass getItem(int position) {
// TODO Auto-generated method stub
        return listDest.get(position);
    }
    //Retourne la position de l'item -----------------------------

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }
}

