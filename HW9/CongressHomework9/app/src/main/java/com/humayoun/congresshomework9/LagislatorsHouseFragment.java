package com.humayoun.congresshomework9;

import android.content.Intent;
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

/**
 * Created by user on 14/11/2016.
 */

public class LagislatorsHouseFragment extends Fragment implements View.OnClickListener {

    ListView list;
    Map<String, Integer> mapIndex;
    ArrayList<Legislators> legislatorArray= new ArrayList<Legislators>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_lagilators_senate, container, false);
        legislatorArray= new ArrayList<Legislators>();


        list = (ListView) v.findViewById(R.id.list);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // String Slecteditem= names.get(position);
                String Slecteditem= legislatorArray.get(position).first_name+legislatorArray.get(position).las_name;
                Log.d("LagislatorsFragment","clicked.....");
                //Toast.makeText(getActivity().getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
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
                try {


                    HttpGet httppost = new HttpGet("http://csci-hw8.appspot.com/hw8server.php?all=all");
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpResponse response = httpclient.execute(httppost);

                    // StatusLine stat = response.getStatusLine();
                    int status = response.getStatusLine().getStatusCode();

                    if (status == 200) {
                        HttpEntity entity = response.getEntity();
                        String data = EntityUtils.toString(entity);


                        //JSONObject jsono = new JSONObject(data);

                        msg=data;
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {


                JsonParser parser = new JsonParser();
                // The JsonElement is the root node. It can be an object, array, null or
                // java primitive.
                JsonElement element = parser.parse(msg);
                //Log.d("LagislatorsFragment","Committee jsonELEMENT is: "+element);

                //JsonObject albums = element.getAsJsonObject();
                //Log.d("LagislatorsFragment","Committee jsonAlbum is: "+albums);

                JsonArray results = element.getAsJsonArray();
                //JsonArray datasets = albums.getAsJsonArray("results");

                for (int i = 0; i < results.size(); i++) {
                    JsonObject lagislator = results.get(i).getAsJsonObject();
                    Log.d("LagislatorsFragment","Committee jsonobject is: "+lagislator.get("last_name"));


                    Legislators l = new Legislators();
                    l.first_name=lagislator.get("first_name").getAsString();
                    l.las_name=lagislator.get("last_name").getAsString();
                    l.party=lagislator.get("party").getAsString();
                    l.state=lagislator.get("state_name").getAsString();
                    l.bioID=lagislator.get("bioguide_id").getAsString();

                    l.chamber=lagislator.get("chamber").getAsString();
                    l.startTerm=lagislator.get("term_start").getAsString();
                    l.endTerm=lagislator.get("term_end").getAsString();

                    l.office=lagislator.get("office").getAsString();
                    l.birthday=lagislator.get("birthday").getAsString();

                    l.contact=lagislator.get("phone").getAsString();

                    l.title=lagislator.get("title").getAsString();

                    if(lagislator.has("oc_email") )
                        if(!lagislator.get("oc_email").isJsonNull())
                            l.email=lagislator.get("oc_email").getAsString();

                    if(lagislator.has("facebook_id") )
                        if(!lagislator.get("facebook_id").isJsonNull())
                            l.facebook=lagislator.get("facebook_id").getAsString();

                    if(lagislator.has("twitter_id") )
                        if(!lagislator.get("twitter_id").isJsonNull())
                            l.twitter=lagislator.get("twitter_id").getAsString();

                    if(lagislator.has("website") )
                        if(!lagislator.get("website").isJsonNull())
                            l.website=lagislator.get("website").getAsString();

                    if(lagislator.has("fax") )
                        if(!lagislator.get("fax").isJsonNull())
                            l.fax=lagislator.get("fax").getAsString();

                    if(lagislator.has("district") )
                        if(!lagislator.get("district").isJsonNull())
                            l.district= lagislator.get("district").getAsInt();
                    //https://theunitedstates.io/images/congress/225x275/bioguide_id.jpg
                    l.imgURL="https://theunitedstates.io/images/congress/225x275/"+l.bioID+".jpg";

                    if(l.chamber.equalsIgnoreCase("house")){
                        legislatorArray.add(l);

                    }



                }

                Collections.sort(legislatorArray, new Comparator<Legislators>(){
                    public int compare(Legislators obj1, Legislators obj2)
                    {
                        // TODO Auto-generated method stub
                        return obj1.las_name.compareToIgnoreCase(obj2.las_name);
                    }
                });

                LagislatorListAdapter adapter=new LagislatorListAdapter(getActivity(), legislatorArray);


                list.setAdapter(adapter);
                getIndexList();

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
        LinearLayout indexLayout = (LinearLayout) getActivity().findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getActivity().getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Lagislators");




    }
}