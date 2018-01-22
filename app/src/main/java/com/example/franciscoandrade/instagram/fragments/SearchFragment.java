package com.example.franciscoandrade.instagram.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.franciscoandrade.instagram.Adapters.AdapterSearch;
import com.example.franciscoandrade.instagram.R;
import com.example.franciscoandrade.instagram.jsonUserSearch.DatumSearch;
import com.example.franciscoandrade.instagram.jsonUserSearch.RootObjectSearchUser;
import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.example.franciscoandrade.instagram.restApi.EndPointApi;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    View v;
    Retrofit retrofit;
    RecyclerView recyclerSearch;
    AdapterSearch adapterSearch;
    TextInputEditText searchTerm;
    //List<DatumSearch> newList= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerSearch = (RecyclerView) v.findViewById(R.id.recyclerSearch);
        searchTerm = (TextInputEditText) v.findViewById(R.id.searchTerm);
        adapterSearch = new AdapterSearch(getActivity());
        recyclerSearch.setAdapter(adapterSearch);
        recyclerSearch.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false);
        //final GridLayoutManager gridLayoutManager= new GridLayoutManager(getActivity(), 3);
        recyclerSearch.setLayoutManager(linearLayout);
        SnapHelper snapHelper2 = new GravitySnapHelper(Gravity.TOP);
        snapHelper2.attachToRecyclerView(recyclerSearch);
        retrofitSearchUser();
        keyTextListener();
        //        findApiToken();
//        readFromFile(getActivity());
        //Log.d("TOKEN#", "onCreateView: "+ Fileread.getFile());
        return v;
    }

    private void keyTextListener() {
        searchTerm.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == keyEvent.ACTION_DOWN) {
                    if (i == keyEvent.KEYCODE_ENTER && !TextUtils.isEmpty(searchTerm.getText())) {
                        String searchTermString = searchTerm.getText().toString();
                        obtenerDatos(searchTermString);
                        searchTerm.setText("");
                        hideSoftKeyboard(getActivity());
                    }
                }
                return false;
            }
        });
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }


    private void retrofitSearchUser() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL_UNSPLASH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void obtenerDatos(String userName) {

        String url = ConstantsRestApi.ROOT_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EndPointApi service = retrofit.create(EndPointApi.class);
        Call<RootObjectSearchUser> response = service.getSearchUser(userName, ConstantsRestApi.ACCESS_TOKEN);
        response.enqueue(new Callback<RootObjectSearchUser>() {
            @Override
            public void onResponse(Call<RootObjectSearchUser> call, Response<RootObjectSearchUser> response) {
//                    Log.d("SEARCH", "onResponse: "+response.body().getData().get(0));
                if (response.isSuccessful() && response.body().getData().size() > 0) {
                    // List<DatumSearch> newList= new ArrayList<>();
                    // JSONArray jsonArray= new JSONArray(response.body().getData());
                    //JSONObject jsonObject= new JSONObject(response.body());
                    List<DatumSearch> newList = new ArrayList<>();
                    newList = response.body().getData();
                    Log.d("SIZEEE==", "onResponse: " + newList.size());
                    //newList.addAll(response.body().getData());
                    adapterSearch.addImages(newList);
                } else {
                    showSnackBar();
                }
            }

            @Override
            public void onFailure(Call<RootObjectSearchUser> call, Throwable t) {
                Log.d("FAILSEARCH", "onFailure: " + t.getMessage());
            }
        });
    }


    private void showSnackBar() {
        // LinearLayout mainActivity = (LinearLayout) findViewById(R.id.mainActivity);
        Snackbar snackbar = Snackbar.make(v, "No Results Found", Snackbar.LENGTH_SHORT)
                .setAction("RELOAD", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.show();
    }
}
