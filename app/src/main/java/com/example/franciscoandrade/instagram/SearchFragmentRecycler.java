package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.container.SearchAdapter;
import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.Datum;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import com.example.franciscoandrade.instagram.restApi.usersearchAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */

public class SearchFragmentRecycler extends Fragment {

  View rootView;
  EditText editTextSearch;
  Button buttonSearch;
  RecyclerView recyclerView;
  SearchAdapter searchAdapter;
  ArrayList<Datum> instagramList = new ArrayList<>();
  String userid;
  String token;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_search_fragment_recycler, container, false);

    Bundle bundle = getArguments();
    userid = bundle.getString("userid");
    token = bundle.getString("token");

    return rootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_search);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
    recyclerView.setLayoutManager(gridLayoutManager);
    editTextSearch = rootView.findViewById(R.id.edit_text_search);
    buttonSearch = rootView.findViewById(R.id.button_search);

    internetTask();
    internetTask2();
  }

  public void internetTask() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.instagram.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    usersearchAPI usersearchAPI = retrofit.create(usersearchAPI.class);
    Call<RootObject> searchCall = usersearchAPI.searchUsers(userid, token);

    searchCall.enqueue(new Callback<RootObject>() {
      @Override
      public void onResponse(Call<RootObject> call, Response<RootObject> response) {
        Log.e("TEST", "i connected");
        instagramList.addAll(response.body()
          .getData());
        searchAdapter = new SearchAdapter(instagramList);
        recyclerView.setAdapter(searchAdapter);
        Log.e("TEST", "i connected");
      }

      @Override
      public void onFailure(Call<RootObject> call, Throwable t) {
        Log.e("TEST", "i didn't connect");
      }
    });
  }

  public void internetTask2() {
    Retrofit retrofit2 = new Retrofit.Builder().baseUrl("https://api.instagram.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    usersearchAPI usersearchAPI = retrofit2.create(usersearchAPI.class);

    Call<RootObjectProfile> response2 = usersearchAPI.searchProfile(userid, token);

    response2.enqueue(new Callback<RootObjectProfile>() {
      @Override
      public void onResponse(Call<RootObjectProfile> call, Response<RootObjectProfile> response) {
        RootObjectProfile rootObjectProfile = response.body();
        Log.e("TEST", "internettask2");

        publishProgress(rootObjectProfile);
      }

      @Override
      public void onFailure(Call<RootObjectProfile> call, Throwable t) {
      }
    });
  }

  protected void publishProgress(RootObjectProfile... values) {

    CircleImageView profile_image = (CircleImageView) rootView.findViewById(R.id.searchprofile_image);
    TextView profileName = (TextView) rootView.findViewById(R.id.searchprofileName);
    TextView mediaTV = (TextView) rootView.findViewById(R.id.searchmediaTV);
    TextView followed_byTV = (TextView) rootView.findViewById(R.id.searchfollowed_byTV);
    TextView followsTV = (TextView) rootView.findViewById(R.id.searchfollowsTV);

    Picasso.with(getActivity())
      .load(values[0].getData()
        .getProfile_picture())
      .transform(new CropCircleTransformation())
      .into(profile_image);
    profileName.setText(String.valueOf(values[0].getData()
      .getFull_name()));

    followed_byTV.setText(String.valueOf(values[0].getData().counts.getFollowed_by()));
    followsTV.setText(String.valueOf(values[0].getData().counts.getFollows()));
    mediaTV.setText(String.valueOf(values[0].getData().counts.getMedia()));
  }
}
