package com.humayoun.congresshomework9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommitteeInfoActivity extends AppCompatActivity {

    ArrayList<Committee> CommitteeFavoriteArray= new ArrayList<Committee>();
    boolean starred=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_info);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);







            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            Bundle bundle = getIntent().getExtras();
            String CommitteeString = bundle.getString("SelectedCommittee");
            Gson gson = new Gson();
            final Committee committee = gson.fromJson(CommitteeString, Committee.class);

            TextView nameTextView = (TextView) findViewById(R.id.name);
            TextView committeeIDTextView = (TextView) findViewById(R.id.committeeid);
            TextView chamberTextView = (TextView) findViewById(R.id.chamber);
            TextView contactTextView = (TextView) findViewById(R.id.contact);
            TextView parentCommitteeTextView = (TextView) findViewById(R.id.parentcommittee);

            TextView officeTextView = (TextView) findViewById(R.id.office);
        ImageView chamberImage = (ImageView) findViewById(R.id.imageView2);
            final  ImageView favoriteImage = (ImageView) findViewById(R.id.imageView12);

        // load favorites
        SharedPreferences prefs = getSharedPreferences("Favorites", MODE_PRIVATE);
        String CommitteeFavoriteArrayString = prefs.getString("CommitteeFavoriteArrayString", null);
        if (CommitteeFavoriteArrayString != null) {
            CommitteeFavoriteArray = gson.fromJson(CommitteeFavoriteArrayString,
                    new TypeToken<ArrayList<Committee>>() {
                    }.getType());

            boolean contains=false;
            for(Committee c: CommitteeFavoriteArray){

                if(committee.committee_id.equalsIgnoreCase(c.committee_id)){
                    contains=true;

                }

            }
            if(contains) {
                Picasso.with(CommitteeInfoActivity.this).load(android.R.drawable.btn_star_big_on).into(favoriteImage);
                starred = true;
            }

        }


        favoriteImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // do stuff
                starred=!starred;
                if(starred){
                    Picasso.with(CommitteeInfoActivity.this).load(android.R.drawable.btn_star_big_on).into(favoriteImage);
                    CommitteeFavoriteArray.add(committee);


                }
                else{
                    Picasso.with(CommitteeInfoActivity.this).load(android.R.drawable.btn_star_big_off).into(favoriteImage);
                    int in=0;
                    for(int i=0;i<CommitteeFavoriteArray.size();i++){

                        if(committee.committee_id.equalsIgnoreCase(CommitteeFavoriteArray.get(i).committee_id)){
                            in=i;

                        }

                    }
                    CommitteeFavoriteArray.remove(in);

                }
                Gson gson = new Gson();
                // save new favorite list
                String ChangedCommitteeFavoriteArrayString = gson.toJson(CommitteeFavoriteArray);
                SharedPreferences.Editor editor = getSharedPreferences("Favorites", MODE_PRIVATE).edit();
                editor.putString("CommitteeFavoriteArrayString", ChangedCommitteeFavoriteArrayString);
                editor.commit();





            }

        });








        nameTextView.setText(committee.name);
        committeeIDTextView.setText(committee.committee_id);
            chamberTextView.setText(committee.chamber);
            contactTextView.setText(committee.contact);
        parentCommitteeTextView.setText(committee.parent_committee_id);


            officeTextView.setText(committee.office);

        if(committee.chamber.equalsIgnoreCase("house")){
            Picasso.with(this).load(R.drawable.h).into(chamberImage);

        }
        else{
            Picasso.with(this).load(R.drawable.s).into(chamberImage);
        }








    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
