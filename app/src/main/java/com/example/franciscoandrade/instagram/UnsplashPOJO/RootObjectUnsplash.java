package com.example.franciscoandrade.instagram.UnsplashPOJO;

import java.util.List;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class RootObjectUnsplash {

    public int total;
    public int total_pages;
    private List<Result> results;


    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Result> getResults() {
        return results;
    }
}
