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
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

/**
 * Created by user on 18/11/2016.
 */

public class CommitteeHouseFragment extends Fragment {
    ListView list;

    ArrayList<Committee> CommitteeArray= new ArrayList<Committee>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
       // return inflater.inflate(R.layout.fragment_committee_house, container, false);


            //returning our layout file
            //change R.layout.yourlayoutfilename for each of your fragments
            super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.fragment_committee_house, container, false);
        CommitteeArray= new ArrayList<Committee>();


            list = (ListView) v.findViewById(R.id.list);


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


            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    String msg = "";
                    try {


                        HttpGet httppost = new HttpGet("http://csci-hw8.appspot.com/hw8server.php?committee=all");
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
                    // if (!TextUtils.isEmpty(msg)) {
                    // Toast.makeText(getApplicationContext(), msg,
                    // Toast.LENGTH_LONG).show();
                    // }
                    //msg = msg.substring(1, msg.length() - 1);
                    //Log.d("CommitteesFragment","Committee is: "+msg);

                    JsonParser parser = new JsonParser();
                    // The JsonElement is the root node. It can be an object, array, null or
                    // java primitive.
                    JsonElement element = parser.parse(msg);
                    //Log.d("CommitteesFragment","Committee jsonELEMENT is: "+element);

                    //JsonObject albums = element.getAsJsonObject();
                    //Log.d("CommitteesFragment","Committee jsonAlbum is: "+albums);

                    JsonArray results = element.getAsJsonArray();
                    //JsonArray datasets = albums.getAsJsonArray("results");

                    for (int i = 0; i < results.size(); i++) {
                        JsonObject Committee = results.get(i).getAsJsonObject();
                        Log.d("CommitteesFragment","Committee jsonobject is: "+Committee.get("last_name"));


                        Committee c = new Committee();

                        if(Committee.has("chamber") )
                            if(!Committee.get("chamber").isJsonNull())
                                c.chamber=Committee.get("chamber").getAsString();

                        if(Committee.has("committee_id") )
                            if(!Committee.get("committee_id").isJsonNull())
                                c.committee_id=Committee.get("committee_id").getAsString();

                        if(Committee.has("name") )
                            if(!Committee.get("name").isJsonNull())
                                c.name=Committee.get("name").getAsString();

                        if(Committee.has("parent_committee_id") )
                            if(!Committee.get("parent_committee_id").isJsonNull())
                                c.parent_committee_id=Committee.get("parent_committee_id").getAsString();

                        if(Committee.has("office") )
                            if(!Committee.get("office").isJsonNull())
                                c.office=Committee.get("office").getAsString();

                        if(Committee.has("phone") )
                            if(!Committee.get("phone").isJsonNull())
                                c.contact=Committee.get("phone").getAsString();



                        if(c.chamber.equalsIgnoreCase("house")){
                            CommitteeArray.add(c);

                        }


                    }

                    Collections.sort(CommitteeArray, new Comparator<Committee>(){
                        public int compare(Committee obj1, Committee obj2)
                        {
                            // TODO Auto-generated method stub
                            return obj1.name.compareToIgnoreCase(obj2.name);
                        }
                    });

                    CommitteeListAdapter adapter=new CommitteeListAdapter(getActivity(), CommitteeArray);
                    list.setAdapter(adapter);
                    //JsonObject dataset = results.get(0).getAsJsonObject();


                    //Gson gson = new Gson();
                    // Type listType = new TypeToken<ArrayList<Committee>>(){}.getType();

                    // ArrayList<Committee> committeeList = new Gson().fromJson(msg, listType);
                    //ArrayList<Committee> committeeList = gson.fromJson(msg,new TypeToken<ArrayList<Committee>>() {
                    //}.getType());
                    //JSONObject json = new JSONObject();
                    //final JSONObject json = new JSONObject(msg);
                    //JSONObject object = new JSONObject(msg);

                    //Log.d("CommitteesFragment","CommitteeList is: "+committeeList);
                    //Log.d("CommitteesFragment","CommitteeList is: "+committeeList.get(0));


                }
            }.execute(null, null, null);

            return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Committees");




    }
}
