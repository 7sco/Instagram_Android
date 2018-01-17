package com.example.franciscoandrade.instagram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.franciscoandrade.instagram.restApi.model.Fileread;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

/* TODO: Create a layout that will allow the user to search for other users, and also when clicked it will go to a new view where w will get all data from the user clicked and show the data*/
    View v;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_search, container, false);


//        findApiToken();
//        readFromFile(getActivity());

        Log.d("TOKEN#", "onCreateView: "+ Fileread.getFile());

        return v;
    }






}
