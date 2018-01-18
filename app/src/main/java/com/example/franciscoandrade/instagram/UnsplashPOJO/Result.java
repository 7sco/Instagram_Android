package com.example.franciscoandrade.instagram.UnsplashPOJO;

import com.example.franciscoandrade.instagram.jsonAccess.User;

import java.util.Date;
import java.util.List;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class Result
{
    public String id;
    public Date created_at;
    public Date updated_at;
    public int width;
    public int height;
    public String color;
    public String description;
    public Urls urls;
    public List<Object> categories;
    public Links links;
    public boolean liked_by_user;
    public boolean sponsored;
    public int likes;
    public User user;
    public List<Object> current_user_collections;


    public String getId() {
        return id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public Urls getUrls() {
        return urls;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public Links getLinks() {
        return links;
    }

    public boolean isLiked_by_user() {
        return liked_by_user;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public int getLikes() {
        return likes;
    }

    public User getUser() {
        return user;
    }

    public List<Object> getCurrent_user_collections() {
        return current_user_collections;
    }
}
