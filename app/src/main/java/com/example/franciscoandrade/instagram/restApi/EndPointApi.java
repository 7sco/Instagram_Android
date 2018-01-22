package com.example.franciscoandrade.instagram.restApi;

import com.example.franciscoandrade.instagram.JsonComments.RootObjectComments;
import com.example.franciscoandrade.instagram.UnsplashPOJO.RootObjectUnsplash;
import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import com.example.franciscoandrade.instagram.jsonUserSearch.RootObjectSearchUser;
import com.example.franciscoandrade.instagram.pojoUserInfo.RootObjectUser;
import com.example.franciscoandrade.instagram.pojorandomimage.RootObjectRandom;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.franciscoandrade.instagram.restApi.ConstantsRestApi.URL_GET_PROFILE_USER;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public interface EndPointApi {
    //Get media from user
    @GET(ConstantsRestApi.URL_GET_RECENT_MEDIA_USER)
    retrofit2.Call<RootObject> getRecentMedia();

    //Get profile from searched user
    @GET(URL_GET_PROFILE_USER)
    retrofit2.Call<RootObjectProfile> getProfileInfo();

    //Search for Images
    @GET("/search/photos/")
    retrofit2.Call<RootObjectUnsplash> getPopularMedia(@Query("page") int page, @Query("query") String query, @Query("per_page") int pageNumber, @Header("Authorization") String authorization);

    //Get Random Image
    @GET("/photos/random/")
    retrofit2.Call<RootObjectRandom> getRandomMedia(@Header("Authorization") String authorization);

    //SEARCH USER
    @GET(ConstantsRestApi.KEY_SEARCH_USER)
    retrofit2.Call<RootObjectSearchUser> getSearchUser(@Query("q") String search, @Query("access_token") String authorization);



    @GET(ConstantsRestApi.KEY_GET_COMMENTS+"{user-id}/comments/")
    retrofit2.Call<RootObjectComments> getComments(@Path("user-id") String id, @Query("access_token") String authorization);
    //https://api.instagram.com/v1/media/{user-id}/comments?access_token=TOKEN

    @GET(ConstantsRestApi.ROOT_URL+"users/{user-id}/media/recent/"+ConstantsRestApi.KEY_ACCESS_TOKEN+ConstantsRestApi.ACCESS_TOKEN)
    retrofit2.Call<RootObjectUser> getUserMedia(@Path("user-id") String id, @Query("access_token") String authorization);
    //https://api.instagram.com/v1/users/285348435/media/recent/?access_token=285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c

}

