package com.example.franciscoandrade.instagram.restApi;

import com.example.franciscoandrade.instagram.jsonAccesProfile.RootObjectProfile;
import com.example.franciscoandrade.instagram.jsonAccess.RootObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by murodjon.rahimov on 1/20/18.
 */

public interface usersearchAPI {
  @GET("v1/users/{user-id}/media/recent/")
  Call<RootObject> searchUsers(@Path("user-id") String user_id, @Query("access_token") String access_token);

  @GET("v1/users/{user-id}/")
  Call<RootObjectProfile> searchProfile(@Path("user-id") String user_id,@Query("access_token") String access_token);

}
