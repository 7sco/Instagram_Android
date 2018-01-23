package com.example.franciscoandrade.instagram.restApi;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public class ConstantsRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    //xaviandrade14
    public static final String ACCESS_TOKEN = "TOKEN";
    //7scode
    public static final String ACCESS_TOKEN_7SC0DE = "TOKEN";
    //mrahimov1
    public static final String ACCESS_TOKEN_MRAHIMOV1= "TOKEN";
    //randomamy_
    public static final String ACCESS_TOKEN_RANDOMAMY_ = "TOKEN";



    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_INFORMATION_USER = "users/self/media/recent/";
    public static final String KEY_SEARCH_USER = "users/search";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_INFORMATION_USER +
            KEY_ACCESS_TOKEN +
            ACCESS_TOKEN;
    public static final String KEY_GET_PROFILE_INFORMATION = "users/self/";
    public static final String URL_GET_PROFILE_USER = KEY_GET_PROFILE_INFORMATION +
            KEY_ACCESS_TOKEN +
            ACCESS_TOKEN;
    public static final String KEY_GET_PROFILE_SEARCH = ROOT_URL + KEY_SEARCH_USER;
    public static final String ACCESS_TOKEN_UNSPLASH = "Bearer 3f1558d3c2d0ac4f646bc3ee3a744f6de761e20f3bab18266e3dc21d065c44b1";
    public static final String ROOT_URL_UNSPLASH = "https://api.unsplash.com";
    public static final String KEY_GET_COMMENTS = ROOT_URL + "media/";





//Get user media
//https://api.instagram.com/v1/users/285348435/media/recent/?access_token=285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c


    //https://api.instagram.com/v1/media/1572844173039392197_285348435/comments?access_token=285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c


    //Search  User
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    //rootURL+KEY_SEARCH_USER


    //Gets profile user info
//    https://api.instagram.com/v1/users/self/?access_token=285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c


//    https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN

    // https://api.instagram.com/v1/users/self/?access_token=285348435.c2d73f8.49da2ae0b0c14a0b9c17c930b5ef116c


}


