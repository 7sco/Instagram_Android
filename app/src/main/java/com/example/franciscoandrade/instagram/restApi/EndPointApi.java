package com.example.franciscoandrade.instagram.restApi;

import com.example.franciscoandrade.instagram.UnsplashPOJO.RootObjectUnsplash;
import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import com.example.franciscoandrade.instagram.pojorandomimage.RootObjectRandom;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import static com.example.franciscoandrade.instagram.restApi.ConstantsRestApi.URL_GET_PROFILE_USER;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public interface EndPointApi {

    @GET(ConstantsRestApi.URL_GET_RECENT_MEDIA_USER)
    retrofit2.Call<RootObject> getRecentMedia();

    @GET(URL_GET_PROFILE_USER)
    retrofit2.Call<RootObjectProfile> getProfileInfo();

    @GET("/search/photos/")
    retrofit2.Call<RootObjectUnsplash> getPopularMedia(@Query("page") int page, @Query("query") String query, @Header("Authorization") String authorization);

    @GET("/photos/random/")
    retrofit2.Call<RootObjectRandom> getRandomMedia(@Header("Authorization") String authorization);


}
