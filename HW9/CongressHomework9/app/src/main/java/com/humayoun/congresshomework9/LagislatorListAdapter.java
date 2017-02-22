package com.humayoun.congresshomework9;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 14/11/2016.
 */

public class LagislatorListAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Legislators> itemname;


    public LagislatorListAdapter(Activity context, ArrayList<Legislators> itemname) {
        super(context, R.layout.row2_lagislators, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;

    }

//    public View getView(int position,View view,ViewGroup parent) {
//        LayoutInflater inflater=context.getLayoutInflater();
//        View rowView=inflater.inflate(R.layout.row_lagislators, null,true);
//
//        TextView txtTitle = (TextView) rowView.findViewById(R.id.firstLine);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//        TextView extratxt = (TextView) rowView.findViewById(R.id.secondLine);
//
//        txtTitle.setText(itemname.get(position));
////        imageView.setImageResource(imgid[position]);
//        extratxt.setText(itemname.get(position));
//        return rowView;
//
//    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater inflater=context.getLayoutInflater();
            convertView=inflater.inflate(R.layout.row2_lagislators, null,true);

            holder = new HistoryHolder();
            holder.imageIcon = (ImageView)convertView.findViewById(R.id.icon);
            holder.textTitle = (TextView)convertView.findViewById(R.id.firstLine);
            holder.textScore = (TextView)convertView.findViewById(R.id.secondLine);

            convertView.setTag(holder);
        }
        else
        {
            holder = (HistoryHolder)convertView.getTag();
        }

        holder.textTitle.setText(itemname.get(position).las_name+", "+itemname.get(position).first_name);
//        imageView.setImageResource(imgid[position]);
        holder.textScore.setText("("+itemname.get(position).party+")"+itemname.get(position).state+" - District "+itemname.get(position).district);

//        History history = data[position];
//        holder.textScore.setText(history.score);
//        holder.textTitle.setText(history.gametype);
        Picasso.with(this.context).load(itemname.get(position).imgURL).resize(60, 70).placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_gallery).into(holder.imageIcon);


        return convertView;
    }

    static class HistoryHolder
    {
        ImageView imageIcon;
        TextView textTitle;
        TextView textScore;

    }
}