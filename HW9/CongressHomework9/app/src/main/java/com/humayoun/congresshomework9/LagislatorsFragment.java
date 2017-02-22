package com.humayoun.congresshomework9;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
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
import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 14/11/2016.
 */

public class LagislatorsFragment extends Fragment {

    private FragmentTabHost mTabHost;
    //Mandatory Constructor
    public LagislatorsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_committes_3,container, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        //mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragment_committes_3);

        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent3);

        // mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_committes_3);

        mTabHost.addTab(mTabHost.newTabSpec("state").setIndicator("By State"),
                LagislatorsStateFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("senate").setIndicator("House"),
                LagislatorsHouseFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("joint").setIndicator("Senate"),
                LagislatorsSenateFragment.class, null);

        //mTabHost.getTabWidget().getChildAt(mTabHost.getFocusedChild()).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Lagislators");




    }
}