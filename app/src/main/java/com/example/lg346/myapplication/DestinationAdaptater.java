package com.example.lg346.myapplication;

import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DestinationAdaptater extends BaseAdapter {
    List<DestinationClass> listDest;

    // LayoutInflater aura pour mission de charger notre fichier XML
    LayoutInflater inflater;
    /**
     * Elle nous servira à mémoriser les éléments de la liste en mémoire pour
     * qu’à chaque rafraichissement l’écran ne scintille pas
     *
     * @author patrice
     */
    private class ViewHolder {
        TextView Tvtitre;
        TextView Tvlieu;
        TextView Tvdistance;
    }
    public DestinationAdaptater(Context context, List<DestinationClass> objects) {
        inflater = LayoutInflater.from(context);
        this.listDest = objects;
    }

    /**
     * Génère la vue pour un objet
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            Log.v("test", "convertView is null");
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_destination_listview, null);
            holder.Tvtitre = (TextView) convertView.findViewById(R.id.titre);
            holder.Tvlieu = (TextView) convertView.findViewById(R.id.lieu);
            holder.Tvdistance = (TextView) convertView.findViewById(R.id.distance);
            convertView.setTag(holder);
        } else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
        DestinationClass dest = listDest.get(position);
        holder.Tvtitre.setText(dest.getType());
        holder.Tvlieu.setText(dest.getTitle());
        String distString = (dest.getLatitudeDest() == 0 && dest.getLongitudeDest() == 0)?
                "Distance inconnue" : Double.toString(dest.getDistance()) + " km";
        holder.Tvdistance.setText(distString);
        return convertView;
    }

    // Retourne le nombre d'éléments
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
    //Retourne la position de l'item

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }
}

