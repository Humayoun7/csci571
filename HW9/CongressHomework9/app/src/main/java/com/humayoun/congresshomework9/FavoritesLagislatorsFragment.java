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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 14/11/2016.
 */

public class FavoritesLagislatorsFragment extends Fragment implements View.OnClickListener {

    ListView list;

    public ArrayList<Legislators> legislatorArray= new ArrayList<Legislators>();
    public LagislatorListAdapter adapter;
    Map<String, Integer> mapIndex;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_favorites_lagislators, container, false);

        Gson gson = new Gson();
        // load favorites
        SharedPreferences prefs = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("LegislatorFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            legislatorArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Legislators>>() {
                    }.getType());



        }


        list = (ListView) v.findViewById(R.id.list);





        Collections.sort(legislatorArray, new Comparator<Legislators>(){
            public int compare(Legislators obj1, Legislators obj2)
            {
                // TODO Auto-generated method stub
                return obj1.las_name.compareToIgnoreCase(obj2.las_name);
            }
        });
        adapter=new LagislatorListAdapter(getActivity(), legislatorArray);
        list.setAdapter(adapter);

        getIndexList();

       // displayIndex();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // String Slecteditem= names.get(position);
                String Slecteditem= legislatorArray.get(position).first_name+legislatorArray.get(position).las_name;
                Log.d("LagislatorsFragment","clicked.....");
               // Toast.makeText(getActivity().getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String LegislatorJson = gson.toJson(legislatorArray.get(position));
                Intent intent = new Intent(getActivity(), LagislatorInfoActivity.class);
                intent.putExtra("SelectedLegislator", LegislatorJson);
                startActivity(intent);

            }
        });

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";



                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {




                displayIndex();



            }
        }.execute(null, null, null);


        return v;
    }

    private void getIndexList() {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < legislatorArray.size(); i++) {
            String name = legislatorArray.get(i).las_name;
            String index = name.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex( ) {
        LinearLayout indexLayout = (LinearLayout) getActivity().findViewById(R.id.side_index2);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getActivity().getLayoutInflater().inflate(
                    R.layout.side_index_item2, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }

    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        list.setSelection(mapIndex.get(selectedIndex.getText()));
    }



    @Override
    public void onResume() {
        super.onResume();
        // load favorites
        Gson gson = new Gson();
        SharedPreferences prefs = getActivity().getSharedPreferences("Favorites", MODE_PRIVATE);
        String BillsFavoriteArrayString = prefs.getString("LegislatorFavoriteArrayString", null);
        if (BillsFavoriteArrayString != null) {
            legislatorArray = gson.fromJson(BillsFavoriteArrayString,
                    new TypeToken<ArrayList<Legislators>>() {
                    }.getType());



        }


        Collections.sort(legislatorArray, new Comparator<Legislators>(){
            public int compare(Legislators obj1, Legislators obj2)
            {
                // TODO Auto-generated method stub
                return obj1.las_name.compareToIgnoreCase(obj2.las_name);
            }
        });


        adapter=new LagislatorListAdapter(getActivity(), legislatorArray);
        list.setAdapter(adapter);


    }


}