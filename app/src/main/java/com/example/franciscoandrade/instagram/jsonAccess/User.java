package com.example.franciscoandrade.instagram.jsonAccess;

import java.io.Serializable;

/**
 * Created by franciscoandrade on 12/27/17.
 */

public class User implements Serializable
{
    private String id;
    private String full_name;
    private String profile_picture;
    private String username;
    private String name;
    private String first_name;
    private String last_name;
    private String twitter_username;
    private String portfolio_url;
    private ProfileImage profile_image;


    public String getName() {
        return name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getTwitter_username() {
        return twitter_username;
    }

    public String getPortfolio_url() {
        return portfolio_url;
    }

    public String getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getUsername() {
        return username;
    }
}