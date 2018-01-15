package com.example.franciscoandrade.instagram.jsonAccesProfile;

/**
 * Created by franciscoandrade on 1/15/18.
 */

public class RootObjectProfile {

    public Data data;

    public RootObjectProfile(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
