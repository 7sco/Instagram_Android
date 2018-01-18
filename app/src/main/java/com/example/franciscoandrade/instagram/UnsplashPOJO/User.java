package com.example.franciscoandrade.instagram.UnsplashPOJO;

import java.util.Date;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class User
{
    public String id;
    public Date updated_at;
    public String username;
    public String name;
    public String first_name;
    public String last_name;
    public String twitter_username;
    public String portfolio_url;
    public String bio;
    public String location;
    public Links2 links;
    public ProfileImage profile_image;
    public int total_likes;
    public int total_photos;
    public int total_collections;


    public String getId() {
        return id;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getUsername() {
        return username;
    }

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

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public Links2 getLinks() {
        return links;
    }

    public ProfileImage getProfile_image() {
        return profile_image;
    }

    public int getTotal_likes() {
        return total_likes;
    }

    public int getTotal_photos() {
        return total_photos;
    }

    public int getTotal_collections() {
        return total_collections;
    }
}
