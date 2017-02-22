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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by user on 18/11/2016.
 */

public class BillsNewFragment extends Fragment {
    ListView list;

    ArrayList<Bill> BillArray= new ArrayList<Bill>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        // return inflater.inflate(R.layout.fragment_bills_active, container, false);



        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        // return inflater.inflate(R.layout.fragment_Bill_house, container, false);


        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_bills_new, container, false);
        BillArray= new ArrayList<Bill>();


        list = (ListView) v.findViewById(R.id.list);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // String Slecteditem= names.get(position);
                String Slecteditem= BillArray.get(position).billID+BillArray.get(position).chamber;
                Log.d("BillsFragment","clicked.....");
               // Toast.makeText(getActivity().getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                String BillJson = gson.toJson(BillArray.get(position));
                Intent intent = new Intent(getActivity(), BillsInfoActivity.class);
                intent.putExtra("SelectedBill", BillJson);
                startActivity(intent);

            }
        });


        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {


                    HttpGet httppost = new HttpGet("http://csci-hw8.appspot.com/hw8server.php?bills=bills");
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
                //Log.d("BillsFragment","Bill is: "+msg);

                JsonParser parser = new JsonParser();
                // The JsonElement is the root node. It can be an object, array, null or
                // java primitive.
                JsonElement element = parser.parse(msg);
                //Log.d("BillsFragment","Bill jsonELEMENT is: "+element);

                //JsonObject albums = element.getAsJsonObject();
                //Log.d("BillsFragment","Bill jsonAlbum is: "+albums);

                JsonArray results = element.getAsJsonArray();
                //JsonArray datasets = albums.getAsJsonArray("results");

                for (int i = 0; i < results.size(); i++) {
                    JsonObject bill = results.get(i).getAsJsonObject();
                    Log.d("BillsFragment","Bill jsonobject is: "+bill.get("last_name"));


                    Bill b = new Bill();

                    if(bill.has("chamber") )
                        if(!bill.get("chamber").isJsonNull())
                            b.chamber=bill.get("chamber").getAsString();

                    if(bill.has("bill_id") )
                        if(!bill.get("bill_id").isJsonNull())
                            b.billID=bill.get("bill_id").getAsString();

                    if(bill.has("bill_type") )
                        if(!bill.get("bill_type").isJsonNull())
                            b.billType=bill.get("bill_type").getAsString();

                    if(bill.has("short_title") )
                        if(!bill.get("short_title").isJsonNull())
                            b.billtitle=bill.get("short_title").getAsString();



                    if(bill.has("introduced_on") )
                        if(!bill.get("introduced_on").isJsonNull())
                            b.introducedon=getFormattedDate(bill.get("introduced_on").getAsString());

                    if(b.billtitle.equalsIgnoreCase("N.A")){
                        if(bill.has("official_title") )
                            if(!bill.get("official_title").isJsonNull())
                                b.billtitle=bill.get("official_title").getAsString();

                    }

//                        if(bill.has("bill_type") )
//                            if(!bill.get("bill_type").isJsonNull())
//                                b.billType=bill.get("bill_type").getAsString();
//
//                        if(bill.has("chamber") )
//                            if(!bill.get("chamber").isJsonNull())
//                                b.chamber=bill.get("chamber").getAsString();
//
//                        if(bill.has("bill_id") )
//                            if(!bill.get("bill_id").isJsonNull())
//                                b.billID=bill.get("bill_id").getAsString();
//
//                        if(bill.has("bill_type") )
//                            if(!bill.get("bill_type").isJsonNull())
//                                b.billType=bill.get("bill_type").getAsString();
//
//                        if(bill.has("bill_type") )
//                            if(!bill.get("bill_type").isJsonNull())
//                                b.billType=bill.get("bill_type").getAsString();





                    BillArray.add(b);


                }

                Collections.sort(BillArray, new Comparator<Bill>() {
                    DateFormat f = new SimpleDateFormat("MMM dd, yyyy");
                    @Override
                    public int compare(Bill o1, Bill o2) {
                        try {
                            return f.parse(o2.introducedon).compareTo(f.parse(o1.introducedon));
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                });


                BillsListAdapter adapter=new BillsListAdapter(getActivity(), BillArray);
                list.setAdapter(adapter);
                //JsonObject dataset = results.get(0).getAsJsonObject();


                //Gson gson = new Gson();
                // Type listType = new TypeToken<ArrayList<Bill>>(){}.getType();

                // ArrayList<Bill> BillList = new Gson().fromJson(msg, listType);
                //ArrayList<Bill> BillList = gson.fromJson(msg,new TypeToken<ArrayList<Bill>>() {
                //}.getType());
                //JSONObject json = new JSONObject();
                //final JSONObject json = new JSONObject(msg);
                //JSONObject object = new JSONObject(msg);

                //Log.d("BillsFragment","BillList is: "+BillList);
                //Log.d("BillsFragment","BillList is: "+BillList.get(0));


            }
        }.execute(null, null, null);

        return v;




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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Bills");




    }
}
