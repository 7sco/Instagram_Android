package com.example.franciscoandrade.instagram;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.franciscoandrade.instagram.jsonAccess.Datum;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.example.franciscoandrade.instagram.restApi.EndPointApi;
import com.example.franciscoandrade.instagram.restApi.model.ContactResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CardAdapter cardAdapter;
    ArrayList<Datum> cards = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=(RecyclerView)findViewById(R.id.recyclerContainer);
        new Peticion().execute();
        timer();
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);





        //makeRequestWithOkHttp(ConstantsRestApi.ROOT_URL+ConstantsRestApi.URL_GET_RECENT_MEDIA_USER);

    }


    private void timer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Log.d("RESULTS", "AFTER THREAD =====" + cards.size());
                cardAdapter = new CardAdapter(cards, getApplicationContext());
                recyclerView.setAdapter(cardAdapter);

            }
        }, 1000);
    }

    public class Peticion extends AsyncTask<Void, String, Void> {

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
//                    Log.d("RESULT==", "onResponse: "+response.body().getData().size());

                    for (int i=0; i<response.body().getData().size(); i++){
                        cards.add(response.body().getData().get(i));

                        Log.d("RESULT==", "onResponse: "+cards.get(i).toString());

                    }

                }

                @Override
                public void onFailure(Call<RootObject> call, Throwable t) {

                    Log.d("FAIL==", "onFailure: ");
                }
            });


            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Log.d("END==", "onPostExecute: ");
        }
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
