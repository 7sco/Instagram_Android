package com.example.franciscoandrade.instagram.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.Adapters.CardAdapter;
import com.example.franciscoandrade.instagram.CropCircleTransformation;
import com.example.franciscoandrade.instagram.R;
import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.Datum;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.example.franciscoandrade.instagram.restApi.EndPointApi;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
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
public class ProfileFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    CardAdapter cardAdapter;
    ArrayList<Datum> cards2 = new ArrayList<>();
    String name, imageUrl, postNumber, followersNumber, followingNumber;
    CircleImageView profile_image;
    TextView profileName, mediaTV, followed_byTV, followsTV;
    ProgressBar progrssDiscovery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        profile_image = (CircleImageView) v.findViewById(R.id.profile_image);
        profileName = (TextView) v.findViewById(R.id.profileName);
        mediaTV = (TextView) v.findViewById(R.id.mediaTV);
        followed_byTV = (TextView) v.findViewById(R.id.followed_byTV);
        followsTV = (TextView) v.findViewById(R.id.followsTV);
        progrssDiscovery = (ProgressBar) v.findViewById(R.id.progrssDiscovery);

        new Peticion().execute();
        Log.d("IMAGELINK=", "onCreateView: " + imageUrl);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerContainer);
        cardAdapter = new CardAdapter(getActivity());
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper2 = new GravitySnapHelper(Gravity.TOP);
        snapHelper2.attachToRecyclerView(recyclerView);
        //makeRequestWithOkHttp(ConstantsRestApi.ROOT_URL+ConstantsRestApi.URL_GET_RECENT_MEDIA_USER);
        return v;
    }
    private String tokenPicker(String username) {

        switch (username){
            case "xaviandrade14":
                return "285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c";

            case "7sc0de":
                return "6814267018.171f074.be5da75622af4209bcb7b8a93d9c6498";


            case "mrahimov1":
                return "5406911792.f448b8d.a6705f94281f4bda8a8bb4238e7beeca";

            case "randomamy_":
                return "315656640.2a23084.a3bc42e3d993454ba0659379e6df1e13";

            default:
                return "285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c";
        }

    }

    private void timer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
//                Log.d("RESULTS", "AFTER THREAD =====" + cards.size());
//                if (cards.size()==0){
//                    cardAdapter = new CardAdapter(cards2, getActivity());
//                }
//                else {
//                    cardAdapter = new CardAdapter(cards, getActivity());
//                }
//                recyclerView.setAdapter(cardAdapter);
            }
        }, 1000);
    }

    public class Peticion extends AsyncTask<Void, RootObjectProfile, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String url = ConstantsRestApi.ROOT_URL;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointApi service = retrofit.create(EndPointApi.class);
            Call<RootObject> response = service.getRecentMedia();


            progrssDiscovery.setVisibility(View.VISIBLE);

            response.enqueue(new Callback<RootObject>() {
                @Override
                public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                    Log.d("RESULT==", "onResponse: " + response.body().getData().get(0).getUser().getFull_name());
                    //Log.d("RESPONSEE==", "onResponse: ");
                    ArrayList<Datum> cards = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        cards.add(response.body().getData().get(i));
                        //Log.d("RESULT==", "onResponse: "+cards.get(i).toString());
                    }
                    cards2 = cards;
                    cardAdapter.addImages(cards2);
                    Log.d("SIZE1==", "onResponse: " + cards.size());
                    Log.d("SIZE2==", "onResponse: " + cards2.size());
                    progrssDiscovery.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onFailure(Call<RootObject> call, Throwable t) {
                    Log.d("FAIL==", "onFailure: ");
                    progrssDiscovery.setVisibility(View.INVISIBLE);

                }
            });

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointApi service2 = retrofit2.create(EndPointApi.class);
            Call<RootObjectProfile> response2 = service2.getProfileInfo();
            progrssDiscovery.setVisibility(View.VISIBLE);

            response2.enqueue(new Callback<RootObjectProfile>() {
                @Override
                public void onResponse(Call<RootObjectProfile> call, Response<RootObjectProfile> response) {
                    RootObjectProfile rootObjectProfile = response.body();
                    //publishProgress(rootObjectProfile);
                    // Log.d("PROFILE==", "onResponse: "+rootObjectProfile.getData().full_name.toString());
                    imageUrl = rootObjectProfile.getData().getProfile_picture();
                    name = String.valueOf(rootObjectProfile.getData().getFull_name());
                    postNumber = String.valueOf(rootObjectProfile.getData().getCounts().getMedia());
                    followersNumber = String.valueOf(rootObjectProfile.getData().getCounts().getFollowed_by());
                    followingNumber = String.valueOf(rootObjectProfile.getData().getCounts().getFollows());

                    Picasso.with(getActivity()).load(imageUrl)
                            .transform(new CropCircleTransformation()).into(profile_image);
                    profileName.setText(name);
                    //timer();
                    followed_byTV.setText(followersNumber);
                    followsTV.setText(followingNumber);
                    mediaTV.setText(postNumber);
                    progrssDiscovery.setVisibility(View.INVISIBLE);

                    //Add use profile image as icon
//                    BottomNavigationView bottomNavigation=(BottomNavigationView)v.findViewById(R.id.bottomNavigation);
//                    Menu menu= bottomNavigation.getMenu();
//                    MenuItem menuItem=menu.findItem(R.id.profile);
                    //menu.findItem(R.id.profile).setIcon(Picasso.with(getActivity()).load(rootObjectProfile.getData().getProfile_picture().toString()));
                }

                @Override
                public void onFailure(Call<RootObjectProfile> call, Throwable t) {
                    progrssDiscovery.setVisibility(View.INVISIBLE);

                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(RootObjectProfile... values) {


        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            Log.d("END==", "onPostExecute: ");
        }
    }


}
