package com.example.franciscoandrade.instagram.jsonAccess;

/**
 * Created by franciscoandrade on 12/27/17.
 */

public class Caption
{
    private String id;
    private String text;
    private String created_time;
    private From from;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCreated_time() {
        return created_time;
    }

    public From getFrom() {
        return from;
    }
}