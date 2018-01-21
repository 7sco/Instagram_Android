package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

  View rootView;
  Button buttonSearch;
  EditText editText;

  HashMap<String, HashMap<String, String>> hashMapHashMap;
  HashMap<String, String> othermap;
  HashMap<String, String> othermap1;

  String userID;
  String token;

  public SearchFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_search, container, false);
    hashMapHashMap = new HashMap<>();
    othermap = new HashMap<>();
    othermap1 = new HashMap<>();

    othermap1.put("5406911792", "5406911792.f448b8d.a6705f94281f4bda8a8bb4238e7beeca");
    othermap.put("285348435", "285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c");

    hashMapHashMap.put("francisco", othermap);
    hashMapHashMap.put("murad", othermap1);
    //hashMapHashMap.put("francisco2", othermap2);

    buttonSearch = (Button) rootView.findViewById(R.id.button_search);
    editText = rootView.findViewById(R.id.edit_text_search);
    buttonSearch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SearchFragmentRecycler fragment = new SearchFragmentRecycler();

        for (Map.Entry<String, HashMap<String, String>> mapEntry : hashMapHashMap.entrySet()) {
          if (editText.getText()
            .toString()
            .equalsIgnoreCase(mapEntry.getKey())) {
            Map<String, String> map = mapEntry.getValue();
            for (Map.Entry<String, String> info : map.entrySet()) {
              userID = info.getKey();
              token = info.getValue();
            }
          }
        }

        Bundle bundle = new Bundle();
        bundle.putString("userid", userID);
        bundle.putString("token", token);
        fragment.setArguments(bundle);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentSearchContainer, fragment)
          .commit();
      }
    });

    return rootView;
  }
}


