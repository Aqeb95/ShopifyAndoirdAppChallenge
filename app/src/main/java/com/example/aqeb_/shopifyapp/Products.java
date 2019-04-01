package com.example.aqeb_.shopifyapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {

    String id;
    String tags;
    String title;
    ArrayList<Variants> variants;
    ArrayList<Images> images;

    public Products(ArrayList<Variants> variants) {
        this.variants = variants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public ArrayList<Variants> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Variants> variants) {
        this.variants = variants;
    }

}
