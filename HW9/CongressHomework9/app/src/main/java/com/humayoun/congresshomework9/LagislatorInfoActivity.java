package com.humayoun.congresshomework9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LagislatorInfoActivity extends AppCompatActivity {

    ArrayList<Legislators> LegislatorFavoriteArray= new ArrayList<Legislators>();
    boolean starred=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagislator_info);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String LegislatorString = bundle.getString("SelectedLegislator");
        Gson gson = new Gson();
        final Legislators legislator = gson.fromJson(LegislatorString, Legislators.class);
        TextView nameTextView = (TextView) findViewById(R.id.email);
        TextView emailTextView = (TextView) findViewById(R.id.name);
        TextView chamberTextView = (TextView) findViewById(R.id.chamber);
        TextView contactTextView = (TextView) findViewById(R.id.contact);
        TextView starttermTextView = (TextView) findViewById(R.id.startterm);
        TextView endtermTextView = (TextView) findViewById(R.id.endterm);
        TextView officeTextView = (TextView) findViewById(R.id.office);
        TextView stateTextView = (TextView) findViewById(R.id.state);
        TextView faxTextView = (TextView) findViewById(R.id.fax);
        TextView birthdayTextView = (TextView) findViewById(R.id.birthday);
        TextView partyTextView = (TextView) findViewById(R.id.editText2);
        ImageView profileImage = (ImageView) findViewById(R.id.imageView3);
        ImageView partyImage = (ImageView) findViewById(R.id.imageView5);
        TextView progressbarText = (TextView) findViewById(R.id.textView5);

        ImageView webImage = (ImageView) findViewById(R.id.imageView9);
        ImageView twitterImage = (ImageView) findViewById(R.id.imageView10);
        ImageView facebookImage = (ImageView) findViewById(R.id.imageView11);
      final  ImageView favoriteImage = (ImageView) findViewById(R.id.imageView12);
//        Picasso.with(LagislatorInfoActivity.this).load(R.drawable.f).into(facebookImage);
//        Picasso.with(LagislatorInfoActivity.this).load(R.drawable.t).into(twitterImage);
//        Picasso.with(LagislatorInfoActivity.this).load(R.drawable.w).into(webImage);

        webImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // do stuff
                if(legislator.website !=null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(legislator.website));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LagislatorInfoActivity.this, "Error: No Website for this lagislator", Toast.LENGTH_SHORT).show();
                }
//                String urlString="http://mysuperwebsite";
//                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setPackage("com.android.chrome");
//                try {
//                    context.startActivity(intent);
//                } catch (ActivityNotFoundException ex) {
//                    // Chrome browser presumably not installed so allow user to choose instead
//                    intent.setPackage(null);
//                    context.startActivity(intent);
//                }

            }

        });

        twitterImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // do stuff
                if(legislator.twitter !=null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+legislator.twitter));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LagislatorInfoActivity.this, "Error: No Twitter for this lagislator", Toast.LENGTH_SHORT).show();
                }
//                String urlString="http://mysuperwebsite";
//                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setPackage("com.android.chrome");
//                try {
//                    context.startActivity(intent);
//                } catch (ActivityNotFoundException ex) {
//                    // Chrome browser presumably not installed so allow user to choose instead
//                    intent.setPackage(null);
//                    context.startActivity(intent);
//                }

            }

        });
        facebookImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // do stuff
                if(legislator.facebook !=null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+legislator.facebook));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LagislatorInfoActivity.this, "Error: No Facebook for this lagislator", Toast.LENGTH_SHORT).show();
                }
//                String urlString="http://mysuperwebsite";
//                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setPackage("com.android.chrome");
//                try {
//                    context.startActivity(intent);
//                } catch (ActivityNotFoundException ex) {
//                    // Chrome browser presumably not installed so allow user to choose instead
//                    intent.setPackage(null);
//                    context.startActivity(intent);
//                }

            }

        });


        // load favorites
        SharedPreferences prefs = getSharedPreferences("Favorites", MODE_PRIVATE);
        String LegislatorFavoriteArrayString = prefs.getString("LegislatorFavoriteArrayString", null);
        if (LegislatorFavoriteArrayString != null) {
            LegislatorFavoriteArray = gson.fromJson(LegislatorFavoriteArrayString,
                    new TypeToken<ArrayList<Legislators>>() {
                    }.getType());

            boolean contains=false;
            for(Legislators l: LegislatorFavoriteArray){

                if(legislator.bioID.equalsIgnoreCase(l.bioID)){
                    contains=true;

                }

            }
            if(contains) {
                Picasso.with(LagislatorInfoActivity.this).load(android.R.drawable.btn_star_big_on).into(favoriteImage);
                starred = true;
            }

        }


        favoriteImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // do stuff
                starred=!starred;
                if(starred){
                    Picasso.with(LagislatorInfoActivity.this).load(android.R.drawable.btn_star_big_on).into(favoriteImage);
                    LegislatorFavoriteArray.add(legislator);


                }
                else{
                    Picasso.with(LagislatorInfoActivity.this).load(android.R.drawable.btn_star_big_off).into(favoriteImage);
                    int in=0;
                    for(int i=0;i<LegislatorFavoriteArray.size();i++){

                        if(legislator.bioID.equalsIgnoreCase(LegislatorFavoriteArray.get(i).bioID)){
                            in=i;

                        }

                    }
                    LegislatorFavoriteArray.remove(in);

                }
                Gson gson = new Gson();
                // save new favorite list
                String ChangedLegislatorFavoriteArrayString = gson.toJson(LegislatorFavoriteArray);
                SharedPreferences.Editor editor = getSharedPreferences("Favorites", MODE_PRIVATE).edit();
                editor.putString("LegislatorFavoriteArrayString", ChangedLegislatorFavoriteArrayString);
                editor.commit();





            }

        });



        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);



        nameTextView.setText(legislator.title+" "+legislator.las_name+", "+legislator.first_name);
        emailTextView.setText(legislator.email);
        chamberTextView.setText(legislator.chamber);
        contactTextView.setText(legislator.contact);
        starttermTextView.setText(getFormattedDate(legislator.startTerm));

        endtermTextView.setText(getFormattedDate(legislator.endTerm));
        officeTextView.setText(legislator.office);
        stateTextView.setText(legislator.state);
        faxTextView.setText(legislator.fax);
        birthdayTextView.setText(getFormattedDate(legislator.birthday));
        Picasso.with(this).load(legislator.imgURL).placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_gallery).into(profileImage);
        if(legislator.party.equalsIgnoreCase("D")){
            Picasso.with(this).load(R.drawable.d).into(partyImage);
            partyTextView.setText("Democrat");

        }
        int progress= (int) getTerm(legislator.startTerm,legislator.endTerm);
        progressBar.setProgress((int) getTerm(legislator.startTerm,legislator.endTerm));
        progressbarText.setText(progress+"%");

        //progressBar.setText(getTerm(legislator.startTerm,legislator.endTerm) + " %");




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

    public String getFormattedDate(String oldDateString){

        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "MMM dd, yyyy";

// August 12, 2010
      //   oldDateString = "12/08/2010";
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d= new Date();
        try {
             d = sdf.parse(oldDateString);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);

        return newDateString;
    }

    public double getTerm(String start, String end){
        double term=0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=new Date();
        Date NowDate = new Date();
        Date endDate=new Date();
        try{
         startDate = df.parse(start);
            endDate=df.parse(end);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
       double nowmillis = NowDate.getTime() - startDate.getTime();
       double endmili = endDate.getTime() - startDate.getTime();
        term =  (nowmillis/endmili)*100;
        //Log.d("LagislatorInfoActivity","endmili"+endmili+" startDate"+startDate+" term2"+term);
//        var d1 = new Date(term_start);
//        var d2 = new Date();
//        var nowMiliseconds = d2-d1;
//        var d3 = new Date(term_end);
//
//        var endMiliseconds = d3-d1;
//        var duration = (nowMiliseconds/endMiliseconds)*100;

        return term;
    }
}
