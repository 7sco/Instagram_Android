package com.example.franciscoandrade.instagram.pojoUserInfo;

import com.example.franciscoandrade.instagram.JsonComments.Meta;

import java.util.List;

/**
 * Created by franciscoandrade on 1/22/18.
 */

public class RootObjectUser {

    public List<DataUSer> data;
    public Meta meta;

    public List<DataUSer> getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }
}
