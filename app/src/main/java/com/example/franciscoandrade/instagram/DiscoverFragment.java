package com.example.franciscoandrade.instagram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

/* TODO: Create a fragment that will show popular instagram images, when clicked it will show the image with its comments OR the owners profile page */
//    https://temboo.com/twyla/instagram
    //from what I have read from instagram api they dont allow this but ypu can look for 3rd party api that can give u that data such as the link above
    //I have not tried but u can give it a try


    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

}
