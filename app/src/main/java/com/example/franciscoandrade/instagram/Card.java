package com.example.franciscoandrade.instagram;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public class Card {

    private String id;
    private String nombreCompleto;
    private String imageUrl;
    private int likes=0;

    public Card(String id, String nombreCompleto, String imageUrl, int likes) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.imageUrl = imageUrl;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
