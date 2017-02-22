package com.humayoun.congresshomework9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 18/11/2016.
 */

public class FavoritesBillsFragment extends Fragment {
    ListView list;

    public static ArrayList<Bill> BillArray= new ArrayList<Bill>();
    public static BillsListAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //change R.layout.yourlayoutfilename for each of your fragments
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_bills_active, container, false);

        Gson gson = new Gson();
        // load favorites
        SharedPreferences prefs = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("BillsFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            BillArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Bill>>() {
                    }.getType());



        }



        list = (ListView) v.findViewById(R.id.list);

        Collections.sort(BillArray, new Comparator<Bill>() {
            DateFormat f = new SimpleDateFormat("MMM dd, yyyy");
            @Override
            public int compare(Bill o1, Bill o2) {
                try {
                    return f.parse(o1.introducedon).compareTo(f.parse(o2.introducedon));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        adapter=new BillsListAdapter(getActivity(), BillArray);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // String Slecteditem= names.get(position);
                String Slecteditem= BillArray.get(position).billID+BillArray.get(position).chamber;
                Log.d("BillsFragment","clicked.....");
                //Toast.makeText(getActivity().getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String BillJson = gson.toJson(BillArray.get(position));
                Intent intent = new Intent(getActivity(), BillsInfoActivity.class);
                intent.putExtra("SelectedBill", BillJson);
                startActivity(intent);

            }
        });




        return v;




    }



    @Override
    public void onResume() {
        super.onResume();
        // load favorites
        Gson gson = new Gson();
        SharedPreferences prefs = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("BillsFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            BillArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Bill>>() {
                    }.getType());



        }

        Collections.sort(BillArray, new Comparator<Bill>() {
            DateFormat f = new SimpleDateFormat("MMM dd, yyyy");
            @Override
            public int compare(Bill o1, Bill o2) {
                try {
                    return f.parse(o1.introducedon).compareTo(f.parse(o2.introducedon));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });


        adapter=new BillsListAdapter(getActivity(), BillArray);
        list.setAdapter(adapter);


    }


}
