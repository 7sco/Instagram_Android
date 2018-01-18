package com.example.franciscoandrade.instagram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.franciscoandrade.instagram.UnsplashPOJO.Result;
import com.example.franciscoandrade.instagram.UnsplashPOJO.RootObjectUnsplash;
import com.example.franciscoandrade.instagram.pojorandomimage.RootObjectRandom;
import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.example.franciscoandrade.instagram.restApi.EndPointApi;
import com.squareup.picasso.Picasso;

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
public class DiscoverFragment extends Fragment {

/* TODO: Create a fragment that will show popular instagram images, when clicked it will show the image with its comments OR the owners profile page */
//    https://temboo.com/twyla/instagram
    //from what I have read from instagram api they dont allow this but ypu can look for 3rd party api that can give u that data such as the link above
    //I have not tried but u can give it a try


    View v;
    Retrofit retrofit;
    RecyclerView recyclerView;
    AdapterImages adapterImages;
    private boolean aptoParaCargar;
    private int offset;
    List<Result> newList= new ArrayList<>();
    public DiscoverFragment() {
        // Required empty public constructor
    }

    ImageView randomImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    v= inflater.inflate(R.layout.fragment_discover, container, false);

        randomImage=(ImageView)v.findViewById(R.id.randomImage);
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerDiscover);
        // new Peticion().execute();
//        timer();
        adapterImages = new AdapterImages( getActivity());
        recyclerView.setAdapter(adapterImages);
        recyclerView.setHasFixedSize(true);
        //final LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final GridLayoutManager gridLayoutManager= new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = gridLayoutManager.getChildCount();
                    int totalItemCount = gridLayoutManager.getItemCount();
                    int pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i("END==", " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 1;
                            Log.d("CALL2==", "onScrolled: ");
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });
        retrofitListImages();
        retrofitRandomImage();

        aptoParaCargar = true;
        offset = 1;
        obtenerDatos(offset);


    return v;

    }

    private void retrofitRandomImage() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL_UNSPLASH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void retrofitListImages() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL_UNSPLASH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private void obtenerDatos( int offset) {

        EndPointApi service = retrofit.create(EndPointApi.class);
        Call<RootObjectUnsplash> response = service.getPopularMedia( offset, "selfie", ConstantsRestApi.ACCESS_TOKEN_UNSPLASH);
        response.enqueue(new Callback<RootObjectUnsplash>() {
            @Override
            public void onResponse(Call<RootObjectUnsplash> call, Response<RootObjectUnsplash> response) {
//               Log.d("DISCOVER==", "onResponse: "+response.body().getResults().toString());
                Log.d("Francisco==", "onResponse: ");
               //Log.d("URL==", "onResponse: THIS IS THE URL"+response.body().get(0).getResults().toString());
                Log.d("RESPONDED==", "onResponse: ");
                aptoParaCargar = true;
                if(response.isSuccessful()){
                    for (int i=0; i<response.body().getResults().size();i++){
                        newList.add(response.body().getResults().get(i));
                    }
                    adapterImages.addImages(newList);
                }
            }
            @Override
            public void onFailure(Call<RootObjectUnsplash> call, Throwable t) {

            }
        });


        EndPointApi service2 = retrofit.create(EndPointApi.class);
        Call<RootObjectRandom> response2 = service2.getRandomMedia(ConstantsRestApi.ACCESS_TOKEN_UNSPLASH);
        response2.enqueue(new Callback<RootObjectRandom>() {
            @Override
            public void onResponse(Call<RootObjectRandom> call, Response<RootObjectRandom> response) {
                    Log.d("IMAGE LINK", "onResponse: "+response.body());
                if(response.body()!=null){
                    String urlRandomImage=response.body().getUrls().getRegular().toString();
                    Picasso.with(getActivity()).load(urlRandomImage)
                            .resize(400, 400)
                            .centerInside()
                            .into(randomImage);
                }else {
                    randomImage.setImageResource(R.drawable.cfourq);
                }



            }

            @Override
            public void onFailure(Call<RootObjectRandom> call, Throwable t) {

            }
        });

    }


}
