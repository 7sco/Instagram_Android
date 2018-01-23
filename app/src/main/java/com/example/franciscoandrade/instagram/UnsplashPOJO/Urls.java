package com.example.franciscoandrade.instagram.UnsplashPOJO;

import java.io.Serializable;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class Urls implements Serializable
{
    public String raw;
    public String full;
    public String regular;
    public String small;
    public String thumb;


    public String getRaw() {
        return raw;
    }

    public String getFull() {
        return full;
    }

    public String getRegular() {
        return regular;
    }

    public String getSmall() {
        return small;
    }

    public String getThumb() {
        return thumb;
    }
}
