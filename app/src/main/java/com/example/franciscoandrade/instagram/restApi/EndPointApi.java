package com.example.franciscoandrade.instagram.restApi;

import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import retrofit2.http.GET;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public interface EndPointApi {

    @GET(ConstantsRestApi.URL_GET_RECENT_MEDIA_USER)
    retrofit2.Call<RootObject> getRecentMedia();

    @GET(ConstantsRestApi.URL_GET_PROFILE_USER)
    retrofit2.Call<RootObjectProfile> getProfileInfo();

}
