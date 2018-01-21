package com.example.franciscoandrade.instagram.restApi;

import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import retrofit2.http.GET;

/**
 * Created by murodjon.rahimov on 1/19/18.
 */

public interface Instagram {

  @GET(ConstantsRestApi.URL_GET_RECENT_MEDIA_USER)
  retrofit2.Call<RootObject> getRecentMedia();

  @GET(ConstantsRestApi.URL_GET_PROFILE_USER)
  retrofit2.Call<RootObjectProfile> getProfileInfo();

  }


