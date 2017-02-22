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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 18/11/2016.
 */

public class FavoritesCommitteesFragment extends Fragment {
    ListView list;

   public ArrayList<Committee> CommitteeArray= new ArrayList<Committee>();
    public CommitteeListAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        // return inflater.inflate(R.layout.fragment_committee_house, container, false);


        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_committee_house, container, false);

        Gson gson = new Gson();
        // load favorites
        SharedPreferences prefs = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("CommitteeFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            CommitteeArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Committee>>() {
                    }.getType());



        }


        list = (ListView) v.findViewById(R.id.list);

        Collections.sort(CommitteeArray, new Comparator<Committee>(){
            public int compare(Committee obj1, Committee obj2)
            {
                // TODO Auto-generated method stub
                return obj1.name.compareToIgnoreCase(obj2.name);
            }
        });
        adapter=new CommitteeListAdapter(getActivity(), CommitteeArray);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // String Slecteditem= names.get(position);
                String Slecteditem= CommitteeArray.get(position).committee_id+CommitteeArray.get(position).chamber;
                Log.d("CommitteesFragment","clicked.....");
               // Toast.makeText(getActivity().getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String CommitteeJson = gson.toJson(CommitteeArray.get(position));
                Intent intent = new Intent(getActivity(), CommitteeInfoActivity.class);
                intent.putExtra("SelectedCommittee", CommitteeJson);
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
        // load favorites
        SharedPreferences prefs = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("CommitteeFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            CommitteeArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Committee>>() {
                    }.getType());



        }

        Collections.sort(CommitteeArray, new Comparator<Committee>(){
            public int compare(Committee obj1, Committee obj2)
            {
                // TODO Auto-generated method stub
                return obj1.name.compareToIgnoreCase(obj2.name);
            }
        });

        adapter=new CommitteeListAdapter(getActivity(), CommitteeArray);
        list.setAdapter(adapter);



    }


}
