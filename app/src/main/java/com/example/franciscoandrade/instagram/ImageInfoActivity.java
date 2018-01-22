package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.Adapters.CommentsAdapter;
import com.example.franciscoandrade.instagram.JsonComments.Data;
import com.example.franciscoandrade.instagram.JsonComments.RootObjectComments;
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

public class ImageInfoActivity extends AppCompatActivity {
    ImageView imageComplete;
    TextView captionTV;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Retrofit retrofit;
    RecyclerView recyclerHolderImageInfo;
    CommentsAdapter commentsAdapter;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_info);
        imageComplete=(ImageView)findViewById(R.id.imageComplete);
        captionTV=(TextView) findViewById(R.id.captionTV);
        recyclerHolderImageInfo=(RecyclerView)findViewById(R.id.recyclerHolderImageInfo);

        commentsAdapter = new CommentsAdapter(this);
        recyclerHolderImageInfo.setAdapter(commentsAdapter);
        recyclerHolderImageInfo.setHasFixedSize(true);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        recyclerHolderImageInfo.setLayoutManager(linearLayout);


        Bundle extras= getIntent().getExtras();
        String imageUrl=extras.getString("imageUrl");
        String caption=extras.getString("caption");
        String comments=extras.getString("comments");
        token=extras.getString("token");
        Picasso.with(this).load(imageUrl).into(imageComplete);
        captionTV.setText(caption);
        showToolBar("", true);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(android.R.color.black));

        Log.d("COMMENT#=", "onCreate: "+ comments);
        Log.d("COMMENT#=", "onCreate: "+ token);


        retrofitConn();
        obtenerDatos(comments, token);


    }

    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

//                Intent intent = new Intent(CurrentActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void retrofitConn() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL_UNSPLASH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private void obtenerDatos(String id, String token) {

        EndPointApi service = retrofit.create(EndPointApi.class);
        Call<RootObjectComments> response = service.getComments(id, token);

        response.enqueue(new Callback<RootObjectComments>() {
            @Override
            public void onResponse(Call<RootObjectComments> call, Response<RootObjectComments> response) {
                Log.d("COMMENTS=", "onResponse: "+response.toString());
                List<Data> listComments= new ArrayList<>();

                //listComments.addAll(response.body().getData());

                if (response.isSuccessful()) {
                    //Log.d("Francisco==", "onResponse: " + response.body().getResults().toString());
                    //Check the clear is working
//                    List<Result> newList = response.body().getResults();
                    listComments = response.body().getData();
                    commentsAdapter.addImages(listComments);
                }

                //Log.d("LOADED", "onResponse: **********"+listComments.get(0).toString());


            }

            @Override
            public void onFailure(Call<RootObjectComments> call, Throwable t) {

            }
        });

    }




}
