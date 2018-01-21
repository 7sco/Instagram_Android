package com.example.franciscoandrade.instagram.restApi;

import com.example.franciscoandrade.instagram.container.Environment;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public final class ConstantsRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5406911792.f448b8d.a6705f94281f4bda8a8bb4238e7beeca";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_INFORMATION_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_INFORMATION_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String KEY_GET_PROFILE_INFORMATION = "users/self/";
    public static final String URL_GET_PROFILE_USER = KEY_GET_PROFILE_INFORMATION + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
}


