package com.example.franciscoandrade.instagram.UnsplashPOJO;

import java.io.Serializable;

/**
 * Created by franciscoandrade on 1/18/18.
 */

public class Links implements Serializable
{
    public String self;
    public String html;
    public String download;
    public String download_location;

    public String getSelf() {
        return self;
    }

    public String getHtml() {
        return html;
    }

    public String getDownload() {
        return download;
    }

    public String getDownload_location() {
        return download_location;
    }
}