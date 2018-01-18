package com.example.franciscoandrade.instagram.pojorandomimage;

import com.example.franciscoandrade.instagram.UnsplashPOJO.Links;
import com.example.franciscoandrade.instagram.UnsplashPOJO.Urls;

import java.util.Date;
import java.util.List;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class RootObjectRandom
{
    public String id;
    public Date created_at;
    public Date updated_at;
    public int width;
    public int height;
    public String color;
    public Object description;
    public List<Object> categories;
    public Urls urls;
    public Links links;
    public boolean liked_by_user;
    public boolean sponsored;
    public int likes;
    public User user;
    public List<Object> current_user_collections;
    public Object slug;
    public Location location;
    public Exif exif;
    public int views;
    public int downloads;

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

    public Object getDescription() {
        return description;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public Urls getUrls() {
        return urls;
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

    public Object getSlug() {
        return slug;
    }

    public Location getLocation() {
        return location;
    }

    public Exif getExif() {
        return exif;
    }

    public int getViews() {
        return views;
    }

    public int getDownloads() {
        return downloads;
    }
}