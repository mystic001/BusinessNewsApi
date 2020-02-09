package com.example.businessnewsapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class WholeNews {

    @SerializedName("articles")
    private ArrayList<ArticleNewsModel> model = new ArrayList<>();


    public ArrayList<ArticleNewsModel> getModel() {
        return model;
    }
}
