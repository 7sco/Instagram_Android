package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FrameLayout fragmentContainer;
    BottomNavigationView bottomNavigation;
    ProfileFragment profileFragment = new ProfileFragment();
    SearchFragment searchFragment = new SearchFragment();
    DiscoverFragment discoverFragment = new DiscoverFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToolBar("", false);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, profileFragment).commit();
        bottomNavigation.setSelectedItemId(R.id.profile);
        BottomNavigationItemView bottomNavigationItemView = (BottomNavigationItemView) findViewById(R.id.profile);
        //bottomNavigation.setItemBackgroundResource(R.drawable.ic_search);
//        Drawable is = (Drawable)getResources().getDrawable(R.drawable.ic_search);
//        Drawable myIcon = getResources().getDrawable( R.drawable.ic_search );
//        Menu menu= bottomNavigation.getMenu();
//        menu.findItem(R.id.profile).setIcon(R.drawable.ic_search);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                switch (item.getItemId()) {
                    case R.id.profile:
                        transaction.replace(R.id.fragmentContainer, profileFragment);
                        break;
                    case R.id.search:
                        transaction.replace(R.id.fragmentContainer, searchFragment);
                        break;
                    case R.id.discover:
                        transaction.replace(R.id.fragmentContainer, discoverFragment);
                        break;
                }
                if (transaction != null) {
                    transaction.commit();
                    //If we want to add fragments to stack
                    //transaction.addToBackStack(null).commit();
                }
                return true;
            }
        });
        makeRequestWithOkHttp();
    }

    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void makeRequestWithOkHttp() {
        String url = "https://api.instagram.com/v1/" + ConstantsRestApi.URL_GET_PROFILE_USER;
        Log.d("URL==", "run: " + url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                Log.d("OkHHTP==", "onResponse: " + response.body().string());
                String jsonData = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("data");
                    Log.d("IMAGEURL", "onResponse: " + jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
