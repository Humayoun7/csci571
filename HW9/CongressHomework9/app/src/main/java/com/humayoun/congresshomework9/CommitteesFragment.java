package com.humayoun.congresshomework9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 14/11/2016.
 */

public class CommitteesFragment extends Fragment {

    private FragmentTabHost mTabHost;
    //Mandatory Constructor
    public CommitteesFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        //return inflater.inflate(R.layout.fragment_committes_3, container, false);
        View rootView = inflater.inflate(R.layout.fragment_committes_3,container, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        //mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragment_committes_3);

        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent3);
       // mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_committes_3);

        mTabHost.addTab(mTabHost.newTabSpec("house").setIndicator("House"),
                CommitteeHouseFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("senate").setIndicator("Senate"),
                CommitteeSenateFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("joint").setIndicator("Joint"),
                CommitteeJointFragment.class, null);


        return rootView;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Committees");
    }
}