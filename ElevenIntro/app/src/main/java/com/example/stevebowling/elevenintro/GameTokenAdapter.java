package com.example.stevebowling.elevenintro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by stevebowling on 10/17/16.
 */

public class GameTokenAdapter extends ArrayAdapter<Player> {

    private LayoutInflater inflater;
    private int resource;
    private ArrayList<Player>gameState;

    public GameTokenAdapter(Context context, int resouce, ArrayList<Player> object){

        super(context, resouce, object);

        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource =resouce;
        gameState = object;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holer;

        if (convertView==null){

            convertView = inflater.inflate(resource, parent, false);
            holer= new ViewHolder(convertView);
            convertView.setTag(holer);

        }
        else {
            holer= (ViewHolder)convertView.getTag();
        }

        char player = gameState.get(position).symbol;
        holer.text.setText(String.valueOf(player));
        return convertView;
    }

    private class ViewHolder
    {
        public TextView text;
        public ViewHolder(View view){
            text= (TextView) view.findViewById(R.id. cellText);
        }
    }



}
