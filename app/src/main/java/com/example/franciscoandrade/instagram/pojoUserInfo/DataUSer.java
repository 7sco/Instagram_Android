package com.example.franciscoandrade.instagram.pojoUserInfo;

import com.example.franciscoandrade.instagram.jsonAccess.Caption;
import com.example.franciscoandrade.instagram.jsonAccess.Comments;
import com.example.franciscoandrade.instagram.jsonAccess.Likes;

import java.util.List;

/**
 * Created by franciscoandrade on 1/22/18.
 */

public class DataUSer {
    public String id;
    public User user;
    public Images images;
    public String created_time;
    public Caption caption;
    public boolean user_has_liked;
    public Likes likes;
    public List<Object> tags;
    public String filter;
    public Comments comments;
    public String type;
    public String link;
    public Location location;
    public Object attribution;
    public List<Object> users_in_photo;

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

    public boolean isUser_has_liked() {
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
