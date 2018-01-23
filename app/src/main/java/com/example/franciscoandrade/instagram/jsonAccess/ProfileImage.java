package com.example.franciscoandrade.instagram.jsonAccess;

import java.io.Serializable;

/**
 * Created by franciscoandrade on 1/22/18.
 */

class ProfileImage implements Serializable {
    public String small;
    public String medium;
    public String large;

    public String getSmall() {
        return small;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }
}
