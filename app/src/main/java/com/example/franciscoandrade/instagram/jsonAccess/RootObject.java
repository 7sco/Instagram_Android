package com.example.franciscoandrade.instagram.jsonAccess;

import java.util.List;

/**
 * Created by franciscoandrade on 12/27/17.
 */

public class RootObject {

    public List<Datum> data;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
