package com.example.franciscoandrade.instagram.jsonAccesProfile;

/**
 * Created by franciscoandrade on 1/15/18.
 */

public class Counts {

    private int media;
    private int follows;
    private int followed_by;


    public Counts(int media, int follows, int followed_by) {
        this.media = media;
        this.follows = follows;
        this.followed_by = followed_by;
    }


    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFollowed_by() {
        return followed_by;
    }

    public void setFollowed_by(int followed_by) {
        this.followed_by = followed_by;
    }
}
