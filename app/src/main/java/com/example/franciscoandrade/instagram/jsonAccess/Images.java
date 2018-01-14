package com.example.franciscoandrade.instagram.jsonAccess;

/**
 * Created by franciscoandrade on 12/27/17.
 */

public class Images
{
    public Thumbnail thumbnail;
    public LowResolution low_resolution;
    public StandardResolution standard_resolution;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public LowResolution getLow_resolution() {
        return low_resolution;
    }

    public StandardResolution getStandard_resolution() {
        return standard_resolution;
    }
}
