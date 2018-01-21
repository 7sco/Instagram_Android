package com.example.franciscoandrade.instagram;
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
import android.widget.TextView;

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
    ArrayList<Datum> cards = new ArrayList<>();
    ArrayList<Datum> cards2 = new ArrayList<>();


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v= inflater.inflate(R.layout.fragment_profile, container, false);
        new Peticion().execute();
        timer();
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerContainer);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper2 = new GravitySnapHelper(Gravity.TOP);
        snapHelper2.attachToRecyclerView(recyclerView);
        //makeRequestWithOkHttp(ConstantsRestApi.ROOT_URL+ConstantsRestApi.URL_GET_RECENT_MEDIA_USER);
        return v;
    }


    private void timer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
//                Log.d("RESULTS", "AFTER THREAD =====" + cards.size());
                if (cards.size()==0){
                    cardAdapter = new CardAdapter(cards2, getActivity());
                }
                else {
                    cardAdapter = new CardAdapter(cards, getActivity());
                }
                recyclerView.setAdapter(cardAdapter);
            }
        }, 2000);
    }

    public class Peticion extends AsyncTask<Void, RootObjectProfile, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            String url= ConstantsRestApi.ROOT_URL;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointApi service = retrofit.create(EndPointApi.class);
            Call<RootObject> response = service.getRecentMedia();
            response.enqueue(new Callback<RootObject>() {
                @Override
                public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                    Log.d("RESULT==", "onResponse: "+response.body().getData());
                    //Log.d("RESPONSEE==", "onResponse: ");
                    for (int i=0; i<response.body().getData().size(); i++){
                        cards.add(response.body().getData().get(i));
                        //Log.d("RESULT==", "onResponse: "+cards.get(i).toString());
                    }
                    cards2= cards;

                    Log.d("SIZE1==", "onResponse: "+cards.size());
                    Log.d("SIZE2==", "onResponse: "+cards2.size());
                }
                @Override
                public void onFailure(Call<RootObject> call, Throwable t) {
                    Log.d("FAIL==", "onFailure: ");
                }
            });


            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointApi service2 = retrofit2.create(EndPointApi.class);
            Call<RootObjectProfile> response2 = service2.getProfileInfo();

            response2.enqueue(new Callback<RootObjectProfile>() {
                @Override
                public void onResponse(Call<RootObjectProfile> call, Response<RootObjectProfile> response) {
                    RootObjectProfile rootObjectProfile= response.body();

                    publishProgress(rootObjectProfile);
                    Log.d("PROFILE==", "onResponse: "+rootObjectProfile.getData().full_name.toString());

                }

                @Override
                public void onFailure(Call<RootObjectProfile> call, Throwable t) {

                }
            });

            return null;
        }
        @Override
        protected void onProgressUpdate(RootObjectProfile... values) {

            CircleImageView profile_image= (CircleImageView)v.findViewById(R.id.profile_image);
            TextView profileName=(TextView)v.findViewById(R.id.profileName);
            TextView mediaTV=(TextView)v.findViewById(R.id.mediaTV);
            TextView followed_byTV=(TextView)v.findViewById(R.id.followed_byTV);
            TextView followsTV=(TextView)v.findViewById(R.id.followsTV);

            Picasso.with(getActivity()).load(values[0].getData().getProfile_picture())
                    .transform(new CropCircleTransformation()).into(profile_image);
            profileName.setText(String.valueOf(values[0].getData().getFull_name()));

            followed_byTV.setText(String.valueOf(values[0].getData().counts.getFollowed_by()));
            followsTV.setText(String.valueOf(values[0].getData().counts.getFollows()));
            mediaTV.setText(String.valueOf(values[0].getData().counts.getMedia()));

        }
        @Override
        protected void onPostExecute(Void aVoid) {
//            Log.d("END==", "onPostExecute: ");
        }
    }
}
