package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.Adapters.AdapterUser;
import com.example.franciscoandrade.instagram.pojoUserInfo.DataUSer;
import com.example.franciscoandrade.instagram.pojoUserInfo.RootObjectUser;
import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.example.franciscoandrade.instagram.restApi.EndPointApi;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    Retrofit retrofit;
    List<DataUSer> listUser;
    RecyclerView recyclerUserProfile;
    AdapterUser adapterUser;
    CircleImageView profile_imageUser;
    TextView mediaUserTV, followed_byUserTV, followsUserTV, profileNameUser;
    ProgressBar progrssDiscovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerUserProfile=(RecyclerView)findViewById(R.id.recyclerUserProfile);
        profile_imageUser=(CircleImageView) findViewById(R.id.profile_imageUser);
        mediaUserTV=(TextView) findViewById(R.id.mediaUserTV);
        followed_byUserTV=(TextView) findViewById(R.id.followed_byUserTV);
        followsUserTV=(TextView) findViewById(R.id.followsUserTV);
        profileNameUser=(TextView) findViewById(R.id.profileNameUser);
        progrssDiscovery = (ProgressBar) findViewById(R.id.progrssDiscovery);

        showToolBar("", true);

        adapterUser= new AdapterUser(this);
        recyclerUserProfile.setAdapter(adapterUser);
        recyclerUserProfile.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerUserProfile.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper2 = new GravitySnapHelper(Gravity.TOP);
        snapHelper2.attachToRecyclerView(recyclerUserProfile);



        Bundle extras= getIntent().getExtras();
        String id=extras.getString("id");
        String username=extras.getString("username");

        String token=tokenPicker(username);;

        String prueba =token;

        retrofitUserImage();
        obtenerDatos(id, token);

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

    private String tokenPicker(String username) {
        switch (username){
            case "xaviandrade14":
                return  ConstantsRestApi.ACCESS_TOKEN;
            case "7sc0de":
                return  ConstantsRestApi.ACCESS_TOKEN_7SC0DE;
            case "mrahimov1":
                return  ConstantsRestApi.ACCESS_TOKEN_MRAHIMOV1;
            case "randomamy_":
                return  ConstantsRestApi.ACCESS_TOKEN_RANDOMAMY_;
            default:
                return  ConstantsRestApi.ACCESS_TOKEN;
        }
    }

    private void retrofitUserImage() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL_UNSPLASH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void obtenerDatos(String id, final String token) {
        EndPointApi service = retrofit.create(EndPointApi.class);
        Call<RootObjectUser> response = service.getUserMedia(id, token);
        progrssDiscovery.setVisibility(View.VISIBLE);

        response.enqueue(new Callback<RootObjectUser>() {
            @Override
            public void onResponse(Call<RootObjectUser> call, Response<RootObjectUser> response) {
                Log.d("USUARIOSEARCH", "onResponse: "+response);
                listUser= new ArrayList<>();

                if (response.isSuccessful()) {

                    listUser = response.body().getData();
                    adapterUser.addImages(listUser, token);
                    Picasso.with(getApplicationContext()).load(listUser.get(0).getUser().getProfile_picture())
                            .transform(new CropCircleTransformation()).into(profile_imageUser);
                    profileNameUser.setText(listUser.get(0).getUser().getUsername());
                }
                progrssDiscovery.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<RootObjectUser> call, Throwable t) {
                progrssDiscovery.setVisibility(View.INVISIBLE);

            }
        });

    }


}
