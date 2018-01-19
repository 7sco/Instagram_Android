package com.example.franciscoandrade.instagram.jsonUserSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class RootObjectSearchUser {

    @SerializedName("data")
    @Expose
    private ArrayList<DatumSearch> data = new ArrayList<>();

    public ArrayList<DatumSearch> getData() {
        return data;
    }
}
