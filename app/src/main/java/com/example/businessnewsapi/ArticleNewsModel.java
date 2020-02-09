package com.example.businessnewsapi;


import com.google.gson.annotations.SerializedName;



public class ArticleNewsModel {

    @SerializedName("urlToImage")
    private String imageUrl;

    private String title;

    @SerializedName("author")
    private String Author;
    @SerializedName("url")
    private String uri;

    @SerializedName("publishedAt")
    private String Date ;

    public String DateFormating;



    public ArticleNewsModel() {
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public String getTitle() {
        return title;
    }

    public String getDate() {
        return Date;
    }

    public String getUri() {
        return uri;
    }

    public String getAuthor() {
        return Author;
    }



    public String getDateFormating(String dateStr) {
       DateFormating = dateStr.substring(0,10);
        return DateFormating;
    }

}
