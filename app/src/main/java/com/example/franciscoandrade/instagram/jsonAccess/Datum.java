package com.example.franciscoandrade.instagram.jsonAccess;

import java.util.List;

/**
 * Created by franciscoandrade on 12/27/17.
 */

public class Datum
{
    private String id ;
    private User user ;
    private Images images ;
    private String created_time ;
    private Caption caption ;
    private Boolean user_has_liked ;
    private Likes likes ;
    private List<Object> tags ;
    private String filter ;
    private Comments comments ;
    private String type ;
    private String link ;
    private Location location ;
    private Object attribution ;
    private List<Object> users_in_photo ;

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
