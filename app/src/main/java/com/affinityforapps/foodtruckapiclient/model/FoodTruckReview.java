package com.affinityforapps.foodtruckapiclient.model;

/**
 * Created by stevensherry on 4/26/17.
 */

public class FoodTruckReview {

    private String id = "";
    private String title = "";
    private String text = "";

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public FoodTruckReview(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }
}
