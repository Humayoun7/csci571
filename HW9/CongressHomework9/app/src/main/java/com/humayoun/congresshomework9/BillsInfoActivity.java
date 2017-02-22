package com.humayoun.congresshomework9;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BillsInfoActivity extends AppCompatActivity {

    ArrayList<Bill> BillsFavoriteArray= new ArrayList<Bill>();
    boolean starred=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_info);





            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            Bundle bundle = getIntent().getExtras();
            String BillString = bundle.getString("SelectedBill");
            Gson gson = new Gson();
            final Bill bill = gson.fromJson(BillString, Bill.class);

            TextView titleTextView = (TextView) findViewById(R.id.title);
            TextView BillIDTextView = (TextView) findViewById(R.id.billid);
            TextView chamberTextView = (TextView) findViewById(R.id.chamber);
            TextView sponsorTextView = (TextView) findViewById(R.id.sponsor);
            TextView billtypeTextView = (TextView) findViewById(R.id.billtype);

            TextView statusTextView = (TextView) findViewById(R.id.status);

            TextView introducedonTextView = (TextView) findViewById(R.id.introducedon);
            TextView congressurlTextView = (TextView) findViewById(R.id.congressurl);



            TextView billurlTextView = (TextView) findViewById(R.id.billurl);
        TextView versionstatusTextView = (TextView) findViewById(R.id.versionstatus);




            final  ImageView favoriteImage = (ImageView) findViewById(R.id.imageView12);

        // load favorites
        SharedPreferences prefs = getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("BillsFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            BillsFavoriteArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Bill>>() {
                    }.getType());

            boolean contains=false;
            for(Bill b: BillsFavoriteArray){
                Log.d("BillsInfoActivity","all bills: "+b.billID);
                if(bill.billID.equalsIgnoreCase(b.billID)){
                    contains=true;

                }

            }
            if(contains) {
                Picasso.with(BillsInfoActivity.this).load(android.R.drawable.btn_star_big_on).into(favoriteImage);
                starred = true;
            }

        }




            favoriteImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // do stuff
                    starred=!starred;
                    if(starred){
                        Picasso.with(BillsInfoActivity.this).load(android.R.drawable.btn_star_big_on).into(favoriteImage);
                        BillsFavoriteArray.add(bill);


                    }
                    else{
                        Picasso.with(BillsInfoActivity.this).load(android.R.drawable.btn_star_big_off).into(favoriteImage);
                        int in=0;
                        for(int i=0;i<BillsFavoriteArray.size();i++){

                            if(bill.billID.equalsIgnoreCase(BillsFavoriteArray.get(i).billID)){
                                in=i;

                            }

                        }
                        BillsFavoriteArray.remove(in);

                    }
                    Gson gson = new Gson();
                    // save new favorite list
                    String ChangedBillsFavoriteArrayString = gson.toJson(BillsFavoriteArray);
                    SharedPreferences.Editor editor = getSharedPreferences("Favorites", MODE_PRIVATE).edit();
                    editor.putString("BillsFavoriteArrayString", ChangedBillsFavoriteArrayString);
                    editor.commit();





                }

            });








            BillIDTextView.setText(bill.billID);
            billtypeTextView.setText(bill.billType);
            chamberTextView.setText(bill.chamber);
            titleTextView.setText(bill.billtitle);
            introducedonTextView.setText(bill.introducedon);
        sponsorTextView.setText(bill.sponsor);
        billurlTextView.setText(bill.billUrl);
        congressurlTextView.setText(bill.congressUrl);
        versionstatusTextView.setText(bill.versionStatus);
        statusTextView.setText(bill.status);













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
