package com.example.franciscoandrade.instagram.restApi.model;

import com.example.franciscoandrade.instagram.Card;

import java.util.ArrayList;

/**
 * Created by franciscoandrade on 12/26/17.
 */

public class ContactResponse {

    private static ArrayList<Card> contacts;

    public ArrayList<Card> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Card> contacts) {
        this.contacts = contacts;
    }
}
