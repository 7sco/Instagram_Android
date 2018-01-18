package com.example.franciscoandrade.instagram.restApi;

import android.telecom.Call;

import com.example.franciscoandrade.instagram.Card;
import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import com.example.franciscoandrade.instagram.restApi.model.ContactResponse;

import java.util.ArrayList;

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
