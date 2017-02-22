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
 * Created by user on 20/11/2016.
 */

public class CommitteeListAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<Committee> itemname;


    public CommitteeListAdapter(Activity context, ArrayList<Committee> itemname) {
        super(context, R.layout.row_bill, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommitteeListAdapter.HistoryHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater inflater=context.getLayoutInflater();
            convertView=inflater.inflate(R.layout.row_bill, null,true);

            holder = new CommitteeListAdapter.HistoryHolder();
            holder.thirdline = (TextView)convertView.findViewById(R.id.thirdline);
            holder.textTitle = (TextView)convertView.findViewById(R.id.firstLine);
            holder.textScore = (TextView)convertView.findViewById(R.id.secondLine);

            convertView.setTag(holder);
        }
        else
        {
            holder = (CommitteeListAdapter.HistoryHolder)convertView.getTag();
        }

        holder.textTitle.setText(itemname.get(position).committee_id);
//        imageView.setImageResource(imgid[position]);
        holder.textScore.setText(itemname.get(position).name);
        holder.thirdline.setText(itemname.get(position).chamber);

//        History history = data[position];
//        holder.textScore.setText(history.score);
//        holder.textTitle.setText(history.gametype);

//        Picasso.with(this.context).load(itemname.get(position).imgURL).resize(70, 70).placeholder(R.drawable.ic_menu_gallery)
//                .error(R.drawable.ic_menu_gallery).into(holder.imageIcon);


        return convertView;
    }

    static class HistoryHolder
    {
        TextView textTitle;
        TextView textScore;
        TextView thirdline;
    }
}