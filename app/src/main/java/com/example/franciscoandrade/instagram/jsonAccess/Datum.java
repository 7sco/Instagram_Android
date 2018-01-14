package com.example.franciscoandrade.instagram.jsonAccess;

import java.util.List;

/**
 * Created by franciscoandrade on 12/27/17.
 */

public class Datum
{
    public String id ;
    public User user ;
    public Images images ;
    public String created_time ;
    public Caption caption ;
    public Boolean user_has_liked ;
    public Likes likes ;
    public List<Object> tags ;
    public String filter ;
    public Comments comments ;
    public String type ;
    public String link ;
    public Location location ;
    public Object attribution ;
    public List<Object> users_in_photo ;

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Images getImages() {
        return images;
    }

    public String getCreated_time() {
        return created_time;
    }

    public Caption getCaption() {
        return caption;
    }

    public Boolean getUser_has_liked() {
        return user_has_liked;
    }

    public Likes getLikes() {
        return likes;
    }

    public List<Object> getTags() {
        return tags;
    }

    public String getFilter() {
        return filter;
    }

    public Comments getComments() {
        return comments;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public Location getLocation() {
        return location;
    }

    public Object getAttribution() {
        return attribution;
    }

    public List<Object> getUsers_in_photo() {
        return users_in_photo;
    }
}
