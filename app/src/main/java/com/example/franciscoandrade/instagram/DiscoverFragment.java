package com.example.franciscoandrade.instagram;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.franciscoandrade.instagram.UnsplashPOJO.Result;
import com.example.franciscoandrade.instagram.UnsplashPOJO.RootObjectUnsplash;
import com.example.franciscoandrade.instagram.pojorandomimage.RootObjectRandom;
import com.example.franciscoandrade.instagram.restApi.ConstantsRestApi;
import com.example.franciscoandrade.instagram.restApi.EndPointApi;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
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
    View v;
    LinearLayout searchTagHolder;
    TextInputEditText searchHash;
    String hash = "";
    Retrofit retrofit;
    RecyclerView recyclerView;
    AdapterImages adapterImages;
    private boolean aptoParaCargar;
    private int offset;
    List<Result> newList = new ArrayList<>();
    ImageView randomImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_discover, container, false);
        randomImage = (ImageView) v.findViewById(R.id.randomImage);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerDiscover);
        searchTagHolder = (LinearLayout) v.findViewById(R.id.searchTagHolder);
        searchHash = (TextInputEditText) v.findViewById(R.id.searchHash);
        adapterImages = new AdapterImages(getActivity());
        recyclerView.setAdapter(adapterImages);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        //layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        SnapHelper snapHelper2 = new GravitySnapHelper(Gravity.TOP);
        snapHelper2.attachToRecyclerView(recyclerView);

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
                            obtenerDatos(offset, true);
                        }
                    }
                }
            }
        });
        retrofitListImages();
        retrofitRandomImage();
        aptoParaCargar = true;
        offset = 1;
        hash = "selfie";
        obtenerDatos(offset, true);
        keyTextListener();
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_hash, menu);
    }

    private void keyTextListener() {
        searchHash.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == keyEvent.ACTION_DOWN) {
                    if (i == keyEvent.KEYCODE_ENTER && !TextUtils.isEmpty(searchHash.getText())) {
                        hash = searchHash.getText().toString();
                        aptoParaCargar = false;
                        offset = 1;
                        newList.clear();
                        hideSoftKeyboard(getActivity());
                        searchTagHolder.setVisibility(View.GONE);
                        obtenerDatos(offset, false);
                        searchHash.setText("");
                    }
                }
                return false;
            }
        });
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

    private void obtenerDatos(final int offset, final boolean newWorld) {

        EndPointApi service = retrofit.create(EndPointApi.class);
        Call<RootObjectUnsplash> response = service.getPopularMedia(offset, hash, 15, ConstantsRestApi.ACCESS_TOKEN_UNSPLASH);
        response.enqueue(new Callback<RootObjectUnsplash>() {
            @Override
            public void onResponse(Call<RootObjectUnsplash> call, Response<RootObjectUnsplash> response) {
                Log.d("RESPONDED==", "onResponse: ");
                aptoParaCargar = true;
                if (response.isSuccessful()) {
                    Log.d("Francisco==", "onResponse: " + response.body().getResults().toString());
                    //Check the clear is working
                    List<Result> newList = response.body().getResults();
                    adapterImages.addImages(newList);
                }
                Log.d("OFFSET", "onResponse: " + offset);
            }

            @Override
            public void onFailure(Call<RootObjectUnsplash> call, Throwable t) {
                //aptoParaCargar = true;
            }
        });

        if (offset == 1 || offset % 10 == 0) {
            EndPointApi service2 = retrofit.create(EndPointApi.class);
            Call<RootObjectRandom> response2 = service2.getRandomMedia(ConstantsRestApi.ACCESS_TOKEN_UNSPLASH);
            response2.enqueue(new Callback<RootObjectRandom>() {
                @Override
                public void onResponse(Call<RootObjectRandom> call, Response<RootObjectRandom> response) {
                    Log.d("IMAGE LINK", "onResponse: " + response.body());
                    if (response.body() != null) {
                        String urlRandomImage = response.body().getUrls().getRegular().toString();
                        Picasso.with(getActivity()).load(urlRandomImage)
                                .resize(400, 400)
                                .centerInside()
                                .into(randomImage);
                    } else {
                        randomImage.setImageResource(R.drawable.cfourq);
                    }
                }

                @Override
                public void onFailure(Call<RootObjectRandom> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "Selected: "+item.toString(), Toast.LENGTH_SHORT).show();
        if (searchTagHolder.getVisibility() == View.VISIBLE) {
            searchTagHolder.setVisibility(View.GONE);
        } else {
            searchTagHolder.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
