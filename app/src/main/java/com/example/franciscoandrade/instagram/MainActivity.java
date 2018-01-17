package com.example.franciscoandrade.instagram;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.franciscoandrade.instagram.restApi.model.Fileread;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FrameLayout fragmentContainer;
    BottomNavigationView bottomNavigation;
    ProfileFragment profileFragment= new ProfileFragment();
    SearchFragment searchFragment= new SearchFragment();
    DiscoverFragment discoverFragment= new DiscoverFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToolBar("", false);
        fragmentContainer=(FrameLayout)findViewById(R.id.fragmentContainer);
        bottomNavigation=(BottomNavigationView)findViewById(R.id.bottomNavigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, profileFragment).commit();
        bottomNavigation.setSelectedItemId(R.id.profile);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
//.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                switch (item.getItemId()){
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

        Fileread fi= new Fileread(this);

    }


    private void showToolBar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    private void makeRequestWithOkHttp(String url) {

        Log.d("URL==", "run: "+url);

        OkHttpClient client = new OkHttpClient();   // 1
        Request request = new Request.Builder().url(url).build();  // 2

        client.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) { // 3
                e.printStackTrace();
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                final String result = response.body().string();  // 4

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // perform some ui work with `result`  // 5
                            Log.d("RESULT==", "run: "+result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
